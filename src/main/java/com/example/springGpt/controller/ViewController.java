package com.example.springGpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping(value="/index")
	public String main() {
		return "index";
	}
}
