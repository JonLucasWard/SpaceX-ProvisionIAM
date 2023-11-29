package com.spacex.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.reactive.function.client.WebClient;

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
		 	WebClient.Builder builder = WebClient.builder();
		 	String x = builder
				.build().get().uri(url).retrieve().bodyToMono(String.class).block();
			System.out.println("What I got: " + x);


		model.addAttribute("name", name);
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
		WebClient.Builder builder = WebClient.builder();
		String x = builder
			.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(500*1024)) //launches gives more data than is normally accepted, must overwrite max default
			.build().get().uri(url).retrieve().bodyToMono(String.class).block();
		System.out.println("What I got: " + x);

		model.addAttribute("name", name);
		return "launches"; //return launches.html
	}

}