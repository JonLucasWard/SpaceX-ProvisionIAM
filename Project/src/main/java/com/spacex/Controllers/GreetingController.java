package com.spacex.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.fastjson.JSONObject;

import com.spacex.Objects.Rockets;

@Controller
public class GreetingController {

	/*Mapping for GET HTTP, parameter input "name" 
	 * ThymeLeaf handles fetching of appropriate html page
	*/
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/rockets")
	public String rockets(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		//go to rockets page
		//pull data for all rockets and have it sortable from the front
			//https://api.spacexdata.com/v4/rockets
			//security against malicious data coming in
		//make it pretty

		 	 String url = "https://api.spacexdata.com/v4/rockets";
		 	 WebClient.Builder builder = WebClient.builder(); // instance of webclient
			JSONObject[] response = builder //call URL and assign response to JSON-Object
			 	.build().get().uri(url).retrieve().bodyToMono(JSONObject[].class).block();
			System.out.println(response[0].getString("name"));

			ArrayList<Rockets> rocketList = new ArrayList<Rockets>();

			for(int i = 0;i<response.length;i++){
				Rockets x = new Rockets(response[i]);
				rocketList.add(x);
				System.out.println("in the loop: " + rocketList.get(i).rocket.getString("name"));
			}

			// Collections.sort(rocketList);
			// for(Rockets rocket:rocketList){
			// 	System.out.println("first sort: " + rocket.rocket.getString("name"));
			// }

			// Collections.sort(rocketList, Collections.reverseOrder());
			// for(Rockets rocket:rocketList){
			// 	System.out.println("reverse sort: " + rocket.rocket.getString("name"));
			// }

		model.addAttribute("name", name);
		//model.addAttribute("rockets", response);
		model.addAttribute("rockets", rocketList);
		return "rockets"; //return rockets.html
	}

	@GetMapping("/launches")
	public String launches(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		//go to launches page
		//list all launches per rocket
			//https://api.spacexdata.com/v4/launches
			//security against malicious data coming in
		//make it pretty

		String url = "https://api.spacexdata.com/v4/launches";
		WebClient.Builder builder = WebClient.builder(); //create instance of webclient
		JSONObject[] response = builder //call URL and assign response to JSON-Object
			.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(500*1024)) //launches gives more data than is normally accepted, must overwrite max default
			.build().get().uri(url).retrieve().bodyToMono(JSONObject[].class).block();
		System.out.println(response[0].getJSONObject("auto_update"));

		model.addAttribute("name", name);
		return "launches"; //return launches.html
	}

}