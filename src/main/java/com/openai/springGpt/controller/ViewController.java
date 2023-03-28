package com.openai.springGpt.controller;

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
	
	@GetMapping(value="/demo")
	public String demo() {
		log.debug("demo Page access");
		return "demo";
	}
	
	@GetMapping(value="/testPage")
	public String testPage() {
		log.debug("testPage Page access");
		return "testPage";
	}
	
	@GetMapping(value="/checkbrowser")
	public String checkbrowser() {
		log.debug("checkbrowser Page access");
		return "checkbrowser";
	}
	
}
