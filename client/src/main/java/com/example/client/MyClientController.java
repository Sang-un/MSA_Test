package com.example.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyClientController {

    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @RequestMapping("/")
    public String callService() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        InstanceInfo instanceInfo =
                client.getNextServerFromEureka("service", false);
        String baseUrl = instanceInfo.getHomePageUrl();
        ResponseEntity<String> response =
                restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
        return response.getBody();
    }
}
