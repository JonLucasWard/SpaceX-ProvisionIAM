package com.spacex.Objects;

/*
 * Copyright 2002-2023 Jonathan Ward
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.alibaba.fastjson.JSONObject;

/**
 * Object to allow for easier back-end manipulation of received API launches
 */
public class Launches implements java.lang.Comparable<Launches>{    
    
    public JSONObject launch; //main store of data
    public String firstFlickrImage; //exists in nested JSONArray, must be parsed out
    public String launchDate; //received in non-readable format, must make readable

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

    /**
     * Overrides Comparator method to allow for easy sorting with Collections.sort()
     * We will sort by the date of the launch
     */
    @Override
    public int compareTo(Launches l) {
        return this.launch.getString("date_utc").compareTo(l.launch.getString("date_utc"));
    }
}