package com.example;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoControllerRestTemplateTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RestTemplateBuilder builder;

	private RestTemplate client;

	@BeforeEach
	void setup() {
		this.client = builder.rootUri("http://localhost:" + port + "/").build();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	void getData() {
		ResponseEntity<Map> entity = client.getForEntity("/data", Map.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(entity.getBody()).contains(entry("name", "John Doe"), entry("age", "42"));
	}

}
