package com.spacex;

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

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.*;

import com.spacex.Controllers.spaceXController;

/**
 * Test cases for the spaceX project
 */
@Configuration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	//Resources for tests
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@Autowired
	private spaceXController controller;

	/**
	 * Asserts that we can connect with the controller
	 */
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	/**
	 * Asserts that we can call aunches.html through the appropriate url
	 */
	@Test
	void launchLoads() throws Exception{
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/launches", String.class)).contains("Launches");
	}

	/**
	 * Asserts that we can call rockets.html through the appropriate url
	 */
	@Test
	void rocketLoads() throws Exception{
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/rockets", String.class)).contains("Rockets");
	}

	/**
	 * Asserts that we can call launches API call is active with 200 HTTP response code
	 */
	@Test
	public void launchAPISuccess() {
		WebClient.Builder builder = WebClient.builder();
		Mono<String> response = builder.build().get()
		.uri("https://api.spacexdata.com/v4/launches")
		.exchangeToMono(clientResponse -> {
			assertThat(clientResponse.statusCode().equals(HttpStatus.OK)).isTrue(); //assert must happen as processing during call
			if (clientResponse.statusCode().equals(HttpStatus.OK)) {
				return clientResponse.bodyToMono(String.class);
			}
			else {
				return clientResponse.createError();
			}
		});
	}

	/**
	 * Asserts that we can call rockets API call is active with 200 HTTP response code
	 */
	@Test
	public void rocketAPISuccess() {
		WebClient.Builder builder = WebClient.builder();
		Mono<String> response = builder.build().get()
		.uri("https://api.spacexdata.com/v4/rockets")
		.exchangeToMono(clientResponse -> {
			assertThat(clientResponse.statusCode().equals(HttpStatus.OK)).isTrue(); //assert must happen as processing during call
			if (clientResponse.statusCode().equals(HttpStatus.OK)) {
				return clientResponse.bodyToMono(String.class);
			}
			else {
				return clientResponse.createError();
			}
		});
	}
}
