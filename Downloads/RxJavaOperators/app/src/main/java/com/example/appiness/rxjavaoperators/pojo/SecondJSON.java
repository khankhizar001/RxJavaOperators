package com.example.appiness.rxjavaoperators.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by appiness on 24/4/18.
 */

public class SecondJSON {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }



    public class Item {

        @SerializedName("index")
        @Expose
        private Integer index;
        @SerializedName("friend")
        @Expose
        private String friend;

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getFriend() {
            return friend;
        }

        public void setFriend(String friend) {
            this.friend = friend;
        }

    }
}
