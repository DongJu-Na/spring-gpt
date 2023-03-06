package com.example.springGpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ViewController {
	
	@GetMapping(value="/index")
	public String index() {
    log.debug("index Page access");
		return "index";
	}
	
	@GetMapping(value="/prompt")
	public String prompt() {
		log.debug("prompt Page access");
		return "prompt";
	}
}
