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

import com.alibaba.fastjson.JSONObject;

/**
 * Object to allow for easier back-end manipulation of received API rockets
 */
public class Rockets implements java.lang.Comparable<Rockets>{

    public JSONObject rocket; //stores data object

    public Rockets(JSONObject rocket){ //create the rockets object
        this.rocket = rocket;
    }

    /**
     * Overrides Comparator method to allow for easy sorting with Collections.sort()
     * We will sort by the name of the rocket
     */
    @Override
    public int compareTo(Rockets r) {
        return this.rocket.getString("name").compareTo(r.rocket.getString("name"));
    }
}