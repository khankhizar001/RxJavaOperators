package com.example.appiness.rxjavaoperators;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.appiness.rxjavaoperators.pojo.Price;
import com.example.appiness.rxjavaoperators.pojo.Ticket;
import com.example.appiness.rxjavaoperators.service.APIHelper;
import com.example.appiness.rxjavaoperators.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements FlightAdapter.FlightAdapterListener{
    private static final String TAG = MainActivity.class.getName();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Unbinder unbinder;

    private APIHelper apiHelper;
    private FlightAdapter flightAdapter;


    private ArrayList<Ticket> flightList = new ArrayList<>();

    private static final String from = "DEL";
    private static final String to = "CHE";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(from + " > " + to);

        apiHelper = RetrofitService.getClient().create(APIHelper.class);

        flightAdapter = new FlightAdapter(this,flightList,this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(flightAdapter);

        ConnectableObservable<List<Ticket>> flightObservable = getFlight(from,to).replay();

        disposable.add(flightObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<List<Ticket>>(){

            @Override
            public void onNext(List<Ticket> ticketList) {
                flightList.clear();
                flightList.addAll(ticketList);
                flightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                showError(e);
            }

            @Override
            public void onComplete() {

            }
        }));

        disposable.add(flightObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<Ticket>, ObservableSource<Ticket>>() {
                    @Override
                    public ObservableSource<Ticket> apply(List<Ticket> ticketList) throws Exception {
                        return Observable.fromIterable(ticketList);
                    }
                })
                .flatMap(new Function<Ticket, ObservableSource<Ticket>>() {
                    @Override
                    public ObservableSource<Ticket> apply(Ticket ticket) throws Exception {
                        return getPriceObservable(ticket);
                    }
                })

                .subscribeWith(new DisposableObserver<Ticket>() {

                    @Override
                    public void onNext(Ticket ticket) {
                        int position = flightList.indexOf(ticket);

                        if (position == -1) {
                            // TODO - take action
                            // Ticket not found in the list
                            // This shouldn't happen
                            return;
                        }

                        flightList.set(position, ticket);
                        flightAdapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                }));

        flightObservable.connect();

    }

    private Observable<List<Ticket>> getFlight(String from, String to) {
        return apiHelper.getTickets(from,to)
                //.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Ticket> getPriceObservable(final Ticket ticket) {
        return apiHelper
                .getPrice(ticket.getFlightNumber(), ticket.getFrom(), ticket.getTo())
                //.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Price, Ticket>() {
                    public Ticket apply(Price price) throws Exception{
                       // Log.d("price", price.getPrice().toString());
                        ticket.setPrice(price);
                        return ticket;
                    }
                });

    }

    @Override
    public void onFlightSelected(Ticket ticket) {

    }

    private void showError(Throwable e) {
        Log.e(TAG, "showError: " + e.getMessage());

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}
   
