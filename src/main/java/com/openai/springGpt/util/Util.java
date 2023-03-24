package com.openai.springGpt.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {
	
	/**
	 * 토큰 수 계산 Method
	 * @param sentence
	 * @return
	 */
	public static int countTokens(String sentence) {
		int totalTokens = 0;
		try {
				String[] tokens = sentence.split("[\\p{Punct}\\s]+"); // 구두점과 공백으로 구분된 단어 배열
				int numTokens = tokens.length; // 단어 수
				int numPunct = sentence.replaceAll("\\w", "").length(); // 구두점 수
				int numSpecial = sentence.replaceAll("[\\p{Punct}\\s]+", "").length(); // 특수 문자 수
						totalTokens = numTokens + numPunct + numSpecial; // 토큰 총 수
	    
		} catch (Exception e) {
			log.error(e.getClass() + "에러 발생" );
			e.printStackTrace();
		}
    
    
    return totalTokens;
	}
	
	/**
	 * 해당하는 문장이 있는지 없는지 검사 Method
	 * @param sentence
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "resource", "unused", "rawtypes" })
	public static Boolean containsQuestion(String sentence , String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object> tempMap = new HashMap<String, Object>();
		List<Map<String,Object>> tempList = new ArrayList<>();
		
		boolean result = false;
		
		try {
			  ClassPathResource resource = new ClassPathResource(filePath);
				tempMap = objectMapper.readValue(resource.getFile(), Map.class);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
          String line;

          while ((line = reader.readLine()) != null) {
              Map<String, Object> data = objectMapper.readValue(line, Map.class);
              tempList.add(data);
          }
          
          for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
						Map<String, Object> map = (Map<String, Object>) iterator.next();
						
					}
				
		}catch (Exception e) {
			log.error(e.getClass() + "에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Class to Map 변환 메소드
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> class2Map(Object obj) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
		  for (Field field : obj.getClass().getDeclaredFields()) {
        field.setAccessible(true); // 필드에 접근 가능하도록 설정
        result.put(field.getName(), field.get(obj)); // 필드 이름과 값을 Map에 추가
		  }
		} catch (Exception e) {
			log.error(e.getClass() + "에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString();
	}
	
	

}
