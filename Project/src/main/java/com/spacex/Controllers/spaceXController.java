package com.spacex.Controllers;

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

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.fastjson.JSONObject;

import com.spacex.Objects.Launches;
import com.spacex.Objects.Rockets;

/**
 * Returns associated html after processing api.spacexdata content for screen display after user hits URL endpoint.
 */
@Controller
public class spaceXController {

	/**
	 * Returns processed rocket HTML file using api.spacexdata /rockets endpoint.
	 * @param model Thymeleaf and JavaWeb data to pass to user
	 * @return rockets.html
	 */ 
	@GetMapping("/rockets")
	public String rockets(Model model) {

		 	 String url = "https://api.spacexdata.com/v4/rockets";
		 	 WebClient.Builder builder = WebClient.builder(); // instance of webclient
			JSONObject[] response = builder //call URL and assign response to JSON-Object
			 	.build().get().uri(url).retrieve().bodyToMono(JSONObject[].class).block();

				//Create a proper arralist with these JSONObjects
			ArrayList<Rockets> rocketList = new ArrayList<Rockets>();
			for(int i = 0;i<response.length;i++){
				Rockets x = new Rockets(response[i]);
				rocketList.add(x);
			}
			Collections.sort(rocketList); //Sort the rockets by name BEFORE sending to user

			
		model.addAttribute("rockets", rocketList); //send rocket list with html to user
		return "rockets"; //return rockets.html
	}

	/**
	 * Returns processed launch HTML file using api.spacexdata /launches endpoint.
	 * @param model Thymeleaf and JavaWeb data to pass to user
	 * @return launches.html
	 */ 
	@GetMapping("/launches")
	public String launches(Model model) {

		String url = "https://api.spacexdata.com/v4/launches";
		WebClient.Builder builder = WebClient.builder(); //create instance of webclient
		JSONObject[] response = builder //call URL and assign response to JSON-Object
			.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(500*1024)) //launches gives more data than is normally accepted, must overwrite max default
			.build().get().uri(url).retrieve().bodyToMono(JSONObject[].class).block();

			//Create a proper arraylist with these JSONObjects
			ArrayList<Launches> launchList = new ArrayList<Launches>();
			for(int i = 0;i<response.length;i++){
				Launches x = new Launches(response[i]);
				launchList.add(x);
			}
			Collections.sort(launchList); //Sort the rockets by name BEFORE sending to user

			
		model.addAttribute("launches", launchList); //send launch list with html to user
		return "launches"; //return launches.html
	}

}


