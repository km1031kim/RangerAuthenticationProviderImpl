package org.example;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RestAuthService {

    private static final Logger log = LoggerFactory.getLogger(RestAuthService.class);
    private final String endpoint;
    private final RestTemplate restTemplate;

    public RestAuthService(String endpoint) {
        this.endpoint = endpoint;
        this.restTemplate = new RestTemplate();
    }


    public boolean authenticate(String username, String password) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);

        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);

            return response.getStatusCode().equals(HttpStatus.OK);

        } catch (Exception e) {
            log.warn("Authentication is failed, {}", e.getMessage());
            return false;
        }
    }
}
