package com.spacex.Objects;

import com.alibaba.fastjson.JSONObject;

public class Rockets implements java.lang.Comparable<Rockets>{

    public JSONObject rocket;

    public Rockets(JSONObject rocket){ //create the rockets object
        this.rocket = rocket;
    }

    @Override
    public int compareTo(Rockets r) {
        return this.rocket.getString("name").compareTo(r.rocket.getString("name"));
    }
}