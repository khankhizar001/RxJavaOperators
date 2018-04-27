package com.example.appiness.rxjavaoperators;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.appiness.rxjavaoperators.pojo.Ticket;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by appiness on 26/4/18.
 */

class FlightAdapter  extends RecyclerView.Adapter<FlightAdapter.MyViewHolder>{

    private Context context;
    private List<Ticket> flightList;
    private FlightAdapterListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvAirlineName)
        TextView airlineName;

        @BindView(R.id.ivLogo)
        ImageView logo;

        @BindView(R.id.tvDepTime)
        TextView departureTime;

        @BindView(R.id.tvArrTime)
        TextView arrivalTime;

        @BindView(R.id.tvDuration)
        TextView duration;

        @BindView(R.id.tvStops)
        TextView stops;

        @BindView(R.id.tvPrice)
        TextView price;

        @BindView(R.id.tvSeats)
        TextView seats;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onFlightSelected(flightList.get(getAdapterPosition()));
                }
            });
        }
    }
    public FlightAdapter(Context context, List<Ticket> flightList, FlightAdapterListener listener){

        this.context = context;
        this.flightList = flightList;
        this.listener = listener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Ticket ticket = flightList.get(position);

        Glide.with(context)
                .load(ticket.getAirline().getLogo())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.logo);

        holder.airlineName.setText(ticket.getAirline().getName());
//        holder.departureTime.setText(ticket.getPrice().getPrice());
        holder.departureTime.setText(ticket.getDeparture());
        holder.arrivalTime.setText(ticket.getArrival());
        holder.duration.setText(ticket.getDuration());
        holder.stops.setText(ticket.getNumberOfStops() + " stops ");

        if (ticket.getPrice() != null) {
            //holder.price.setText("₹" + String.format("%.0f", ticket.getPrice().getPrice()));
            holder.price.setText("₹" + String.format("%.0f", ticket.getPrice().getPrice()));
            holder.seats.setText(ticket.getPrice().getSeats() + " Seats");
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }


    public interface FlightAdapterListener {
        void onFlightSelected(Ticket ticket);


    }
}
