package com.spacex.Objects;

import com.alibaba.fastjson.JSONObject;

public class Launches implements java.lang.Comparable<Launches>{    
    
    public JSONObject launch;

    public Launches(JSONObject launch){ //create the rockets object
        this.launch = launch;
    }

    @Override
    public int compareTo(Launches l) {
        return this.launch.getString("date_utc").compareTo(l.launch.getString("date_utc"));
    }
}