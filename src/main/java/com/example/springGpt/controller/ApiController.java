package com.example.springGpt.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dj
 * Api 
 */
@PropertySource("classpath:apikey.yml")
@RestController
@RequestMapping("/api")
public class ApiController {
	
	
	@Value("${openai.api-key:emptykey}")
	private String apiKey;
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/getRecommendedAdText")
	public Map<String,Object> getRecommendedAdText(@RequestBody Map<String, Object> requestParam) throws Exception {
		
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> Header = new HashMap<String, Object>();
													  Header.put("Content-Type","application/json");
														Header.put("Authorization", "Bearer " + apiKey);
														
														
		String content = "브랜드명은 " + requestParam.get("maker")  + "고 " + requestParam.get("sex") + " " + requestParam.get("age")  + " 참고해서 광고 카피라이트 문구 5개 만들어줘 그리고 글머리번호 # 붙여줘";
		List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
    Map<String,Object> tempMap = Map.of( "role" , "user", "content" , content );
    												 tempList.add(tempMap);
    												 
    Map<String,Object> apiParam = new HashMap<String , Object>();    
    												apiParam.put("model", "gpt-3.5-turbo");
    												apiParam.put("messages", tempList);
     
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(apiParam);
					
		
		String gptResult = sendApi("https://api.openai.com/v1/chat/completions", "POST", Header, json);
		if(gptResult != null) {
			result = mapper.readValue(gptResult,Map.class);
		}
		
		return result;
	}
	
	
   /**
    * @param sendUrl
    * @param method
    * @param Header
    * @param jsonValue
    * @return Api Result
    * @throws Exception
    */
	@SuppressWarnings({ "rawtypes", "unchecked" })
   	public static String sendApi(String sendUrl, String method ,Map<String, Object> Header ,String jsonValue) throws Exception  {
       String inputLine = null;
       StringBuffer outResult = new StringBuffer();
       BufferedReader in = null;
       HttpsURLConnection conn = null;
       
         try{
           URL url = new URL(sendUrl);
               conn = (HttpsURLConnection) url.openConnection();
           
           conn.setRequestMethod(method);
               
           if(method.equals("POST")) {
               conn.setDoInput(true);
               conn.setDoOutput(true);
           }else {
               conn.setDoInput(true);
               conn.setDoOutput(false);    
           }
           
          Iterator<?> it = Header.entrySet().iterator();
          while (it.hasNext()) {
               Map.Entry<String, Object> entry = (Map.Entry)it.next();
               conn.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
           }
          
           conn.setConnectTimeout(10000);
           conn.setReadTimeout(10000);
           
           if(method.equals("POST")) {
               OutputStream os = conn.getOutputStream();
               os.write(jsonValue.getBytes("UTF-8"));
               os.flush();
           }
           
           
           // 리턴된 결과 읽기
           int responseCode = conn.getResponseCode();
           if (responseCode == HttpsURLConnection.HTTP_OK) { // 정상 호출 200
               in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
           } else { // 에러 발생 
               in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
           }
           while ((inputLine = in.readLine()) != null) {
               outResult.append(inputLine);
           }
           
           conn.disconnect();
         }catch(Exception e){
             e.printStackTrace();
         }finally {
             if (in != null) {
                 in.close(); 
             } if (conn != null) { 
                conn.disconnect(); 
             }
         }
         
         return outResult.toString();
   }
}
