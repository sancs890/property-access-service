package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.beans.PropertyAccessBean;
import com.example.demo.beans.PropertyAccessValue;

@RestController
@RequestMapping("/access")
public class PropertyFileAccessController {
	@Autowired
	PropertyAccessBean propertyAccessBean;
	@GetMapping("/accessProprtyFile")
	public PropertyAccessValue accessPropertyFile() {
		refreshAcuator();
		return new PropertyAccessValue(propertyAccessBean.getName(), propertyAccessBean.getDescription());
	}
	
	public void refreshAcuator() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8100/actuator/refresh";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity httpEntity = new HttpEntity<>(headers);
		
		ResponseEntity<String> result = restTemplate.postForEntity(baseUrl, httpEntity, String.class);
		
		
		
	}

}
