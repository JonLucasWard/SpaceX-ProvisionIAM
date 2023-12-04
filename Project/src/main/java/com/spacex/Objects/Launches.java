package com.spacex.Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.alibaba.fastjson.JSONObject;

public class Launches implements java.lang.Comparable<Launches>{    
    
    public JSONObject launch;
    public String firstFlickrImage;
    public String launchDate;

    public Launches(JSONObject launch){ //create the rockets object
        this.launch = launch;
            //get human readable date
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime ourDate = LocalDateTime.parse(launch.getString("date_utc"), inputFormatter);
        this.launchDate = outputFormatter.format(ourDate);
            //get first image from flickr, FastJSON alibaba does not process nested ararys in objects
        JSONObject a = launch.getJSONObject("links");
        JSONObject b = a.getJSONObject("flickr");
        String c = b.getString("original");
        try{
            this.firstFlickrImage = c.substring(1,c.indexOf(","));
        } catch(StringIndexOutOfBoundsException e){
            this.firstFlickrImage ="https://upload.wikimedia.org/wikipedia/commons/d/d1/Image_not_available.png";
            
        }
    }

    @Override
    public int compareTo(Launches l) {
        return this.launch.getString("date_utc").compareTo(l.launch.getString("date_utc"));
    }
}