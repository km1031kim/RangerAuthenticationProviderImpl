package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RestAuthServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestAuthService restAuthService;

    private static final String username = "dummyUsername";
    private static final String password = "dummyPassword";
    private static final String endpoint = "dummyEndpoint";


    @Nested
    @DisplayName("인증 성공 케이스")
    class AuthSuccessTest {

        @Test
        @DisplayName("인증 성공")
        void AUTH_OK() {
            // given
            restAuthService = new RestAuthService(endpoint, restTemplate);

            when(restTemplate.exchange(
                    anyString(),
                    eq(HttpMethod.GET),
                    any(HttpEntity.class),
                    eq(String.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

            // when & then
            assertTrue(restAuthService.authenticate(username, password));
        }
    }


    @Nested
    @DisplayName("인증 실패 케이스")
    class AuthFailTest {
        @Test
        @DisplayName("인증 실패 - null 엔드포인트")
        void NULL_ENDPOINT() {
            // given
            restAuthService = new RestAuthService(null);

            // when & then
            assertFalse(restAuthService.authenticate(username, password));
        }


        @Test
        @DisplayName("인증 실패 - 공백으로 이루어진 엔드포인트")
        void EMPTY_ENDPOINT() {
            // given
            restAuthService = new RestAuthService("   ");

            // when & then
            assertFalse(restAuthService.authenticate(username, password));
        }


        @Test
        @DisplayName("인증 실패 - 길이가 0인 엔드포인트")
        void ZERO_LENGTH_ENDPOINT() {
            // given
            restAuthService = new RestAuthService("");

            // when & then
            assertFalse(restAuthService.authenticate(username, password));
        }

        @Test
        @DisplayName("인증 실패 - restTemplate exception")
        void REST_TEMPLATE_API_EXCEPTION() {
            // given
            restAuthService = new RestAuthService(endpoint, restTemplate);

            when(restTemplate.exchange(
                    anyString(),
                    eq(HttpMethod.GET),
                    any(HttpEntity.class),
                    eq(String.class))).thenThrow(RestClientException.class);

            // when & then
            assertFalse(restAuthService.authenticate(username, password));
        }
    }
}