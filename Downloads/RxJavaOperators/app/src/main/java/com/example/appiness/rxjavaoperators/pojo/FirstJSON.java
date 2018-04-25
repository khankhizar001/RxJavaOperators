package com.example.appiness.rxjavaoperators.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by appiness on 24/4/18.
 */

public class FirstJSON {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {

        @SerializedName("index")
        @Expose
        private Integer index;
        @SerializedName("index_start_at")
        @Expose
        private Integer indexStartAt;
        @SerializedName("integer")
        @Expose
        private Integer integer;
        @SerializedName("float")
        @Expose
        private Double _float;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("surname")
        @Expose
        private String surname;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("bool")
        @Expose
        private Boolean bool;

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Integer getIndexStartAt() {
            return indexStartAt;
        }

        public void setIndexStartAt(Integer indexStartAt) {
            this.indexStartAt = indexStartAt;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        public Double getFloat() {
            return _float;
        }

        public void setFloat(Double _float) {
            this._float = _float;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Boolean getBool() {
            return bool;
        }

        public void setBool(Boolean bool) {
            this.bool = bool;
        }

    }
}
