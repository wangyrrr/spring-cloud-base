package org.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class HttpClient {

    @Autowired
    private RestTemplate restTemplate;


    public void doGet(String url, Map<String, String> params) {
//        String uri = "http://localhost:8080/demo/users/{id}";
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", "1");

        long startTime = System.currentTimeMillis();
        String response = restTemplate.getForObject(url, String.class, params);
        long endTime = System.currentTimeMillis();

        log.info("execute GET result: {},latency: {} ms", response, (endTime - startTime));
    }

    public ResponseEntity<String> doPost(String url, String payload) {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("id", "1");

        HttpHeaders headers = initHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(String.format(payload), headers);

        long startTime = System.currentTimeMillis();
//        // 1、使用postForObject请求接口
//        String response1 = restTemplate.postForObject(url, paramMap, String.class);
//        // 2、使用postForEntity请求接口
//        ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
        // 3、使用exchange请求接口
        ResponseEntity<String> response3 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        long endTime = System.currentTimeMillis();
        log.info("execute POST result: {},latency: {} ms", response3.toString(), (endTime - startTime));

        return response3;
    }

    public void doPut(String url, Object obj, Map<String, String> params) {
//        String uri = "http://localhost:8080/demo/user/{id}";
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", "2");
//        User obj = new User(2, "New Name", "Gilly", "test@email.com");

        restTemplate.put(url, obj, params);

    }

    public void doDelete(String url, Map<String, String> params) {
//        String url = "http://localhost:8080/demo/user/{id}";
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", "2");
        restTemplate.delete(url, params);
    }


    private HttpHeaders initHeaders() {
        //headers & cookie
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "");
//        List<String> cookies = new ArrayList<>();
//        headers.put(HttpHeaders.COOKIE, cookies);
        return headers;
    }

}
