package com.openai.springGpt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openai.springGpt.controller.ViewController;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : ViewControllerTest.java
  * @Project : springGpt
  * @Date : 2023. 3. 6. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : view test code
  */
@Slf4j
@DisplayName("View 컨트롤러 테스트")
@WebMvcTest(controllers = ViewController.class)
public class ViewControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@BeforeAll
	public static void init() {
		log.debug("View Page Access Test");
	}
	
	@Test
	public void testIndex() throws Exception {
		mvc.perform(get("/index"))
			 .andDo(print())
			 .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")))
			 .andExpect(status().isOk());
	}
	
	@Test
	public void testPrompt() throws Exception {
		mvc.perform(get("/prompt"))
		 .andDo(print())
		 .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")))
		 .andExpect(status().isOk());
	}

}

