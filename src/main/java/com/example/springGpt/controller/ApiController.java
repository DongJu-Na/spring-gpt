package com.example.springGpt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groovy.util.logging.Slf4j;



@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {
	
	
	@RequestMapping(path = {"/ai"})
	public String testEmail() throws Exception {
		return "test";
	}
	
}
