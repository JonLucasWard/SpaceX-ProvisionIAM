package com.spacex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.spacex.Controllers.spaceXController;

import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.*;


@Configuration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@Autowired
	private spaceXController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void launchLoads() throws Exception{
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/launches", String.class)).contains("Launches");
	}

	@Test
	void rocketLoads() throws Exception{
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/rockets", String.class)).contains("Rockets");
	}

	@Test
	public void launchAPISuccess() {
		WebClient.Builder builder = WebClient.builder();
		Mono<String> response = builder.build().get()
		.uri("https://api.spacexdata.com/v4/launches")
		.exchangeToMono(clientResponse -> {
			assertThat(clientResponse.statusCode().equals(HttpStatus.OK)).isTrue();
			if (clientResponse.statusCode().equals(HttpStatus.OK)) {
				return clientResponse.bodyToMono(String.class);
			}
			else {
				return clientResponse.createError();
			}
		});
	}

	@Test
	public void rocketAPISuccess() {
		WebClient.Builder builder = WebClient.builder();
		Mono<String> response = builder.build().get()
		.uri("https://api.spacexdata.com/v4/rockets")
		.exchangeToMono(clientResponse -> {
			assertThat(clientResponse.statusCode().equals(HttpStatus.OK)).isTrue();
			if (clientResponse.statusCode().equals(HttpStatus.OK)) {
				return clientResponse.bodyToMono(String.class);
			}
			else {
				return clientResponse.createError();
			}
		});
	}


	//test connection to spacex
	//test dummy data displayed to screen

}
