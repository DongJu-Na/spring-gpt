package com.openai.springGpt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.springGpt.SpringGptApplication;
import com.openai.springGpt.controller.ApiController;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : ApiControllerTest.java
  * @Project : springGpt
  * @Date : 2023. 3. 6. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : api test code
  */
@Slf4j
@DisplayName("api 컨트롤러 테스트")
@ContextConfiguration(classes = SpringGptApplication.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@BeforeAll
	public static void init() {
		log.debug("Api Test");
	}
	
	@Value("${openai.api-key:emptykey}")
	private String apiKey;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void apiKeyCheck() throws Exception {
		if(apiKey.equals("emptykey")) {
			log.debug("현재 Open AI Key를 로드 할 수 없습니다. classpath에 apikey.yml를 찾을 수 없습니다.");
		}else {
			log.debug("로드 된 Open AI Key > " + apiKey);
		}
	}
	
	@Test
	public void getRecommendedAdText() throws Exception {
		log.debug("getRecommendedAdText");
			Map<String, Object> param = new HashMap<String, Object>();
													param.put("maker","나이키");
													param.put("age","20대");
													param.put("sex","남성");
			
			mvc.perform(MockMvcRequestBuilders.post("/api/getRecommendedAdText")
				.content(objectMapper.writeValueAsString(param))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
			
	} 
	
	@Test 
	public void getAnswer() throws Exception {
		log.debug("getAnswer");
		
    String[] questions = {
    		"1.인공지능(AI)과 기계학습(ML)의 차이점은 무엇인가요?", 
        "2.GPT는 어떻게 작동하나요?",
        "3.GPT의 역사와 개발 과정은 어떻게 이루어졌나요?",
        "4.GPT의 최신 버전은 어떤 것이 있나요?",
        "5.GPT가 자연어 처리에 어떻게 사용되나요?",
        "6.GPT의 한계는 무엇인가요?",
        "7.GPT-3와 같은 대규모 언어 모델을 구축하는 데 필요한 하드웨어는 어떤 것들이 있나요?",
        "8.GPT-3를 사용하여 어떤 일을 할 수 있나요?",
        "9.GPT와 같은 인공지능 모델이 사회에 미칠 영향에 대해 어떻게 생각하시나요?",
        "10.GPT와 같은 언어 모델을 개발하는 데 사용되는 데이터에 대한 개인정보 보호 문제는 어떻게 다루어지나요?"
        };

		Random random = new Random();
		int index = random.nextInt(10);

		Map<String, Object> param = new HashMap<String, Object>();
												param.put("text",questions[index]);
		
		 mvc.perform(MockMvcRequestBuilders.post("/api/answer")
				.content(objectMapper.writeValueAsString(param))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	
	}

}
