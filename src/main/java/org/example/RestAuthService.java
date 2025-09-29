package org.example;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestAuthService {

    private static final Logger log = LoggerFactory.getLogger(RestAuthService.class);
    private final String endpoint;
    private final RestTemplate restTemplate;

    public RestAuthService(String endpoint) {
        this.endpoint = endpoint;
        this.restTemplate = new RestTemplate();
    }

    // 테스트를 위한 생성자
    public RestAuthService(String endpoint, RestTemplate restTemplate) {
        this.endpoint = endpoint;
        this.restTemplate = restTemplate;
    }


    public boolean authenticate(String username, String password) {

        if (StringUtils.isBlank(endpoint)) {
            log.warn("Authentication is failed. endpoint is empty or null");
            return false;
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

        try {
            ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
            return response.getStatusCode().equals(HttpStatus.OK);
        } catch (RestClientException e) {
            log.warn("Authentication is failed, {}", e.getMessage());
            return false;
        }
    }
}
