package com.example.springGpt.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dj
 * Api 
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
	
	
	@RequestMapping(path = {"/ai"})
	public String getRecommendedAdText() throws Exception {		
		return "test";
	}
	
	
   /**
    * @param sendUrl
    * @param method
    * @param Header
    * @param jsonValue
    * @return Api Result
    * @throws Exception
    */
	@SuppressWarnings("rawtypes")
   	public static String sendApi(String sendUrl, String method ,HashMap<String, Object> Header ,String jsonValue) throws Exception  {
       String inputLine = null;
       StringBuffer outResult = new StringBuffer();
       BufferedReader in = null;
       HttpsURLConnection conn = null;
       
         try{
           //LOGGER.info("sendAPI Start Url " + method + " > " + sendUrl);
           URL url = new URL(sendUrl);
               conn = (HttpsURLConnection) url.openConnection();
           
           //getDoInput() : Server에서 온 데이터를 입력 받을 수 있는 상태인지 여부를 확인한다.(default : true) 
           //getDoOutput(): Server에서 온 데이터를 출력 할수 있는 상태인지 여부를 확인한다.(default : false)
           conn.setRequestMethod(method); // GET,POST
               
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
