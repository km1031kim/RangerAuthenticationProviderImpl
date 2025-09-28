package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class RangerAuthenticationProviderImplTest {


    private RestAuthService restAuthService;

    @Nested
    @DisplayName("인증 성공 테스트")
    class AuthenticationSuccessTest {

        @Test
        @DisplayName("인증 성공")
        public void AUTH_OK() {

        }

    }


    @Nested
    @DisplayName("인증 실패 테스트")
    class AuthenticationFailTest {

        @Test
        @DisplayName("인증 실패 - 인증되지 않은 id, pwd")
        public void AUTH_FAILED() {

        }

        @Test
        @DisplayName("인증 실패 - endpoint url이 빈 문자열인 경우")
        public void URL_IS_EMPTY() {

        }


        @Test
        @DisplayName("인증 실패 - endpoint url이 null 인 경우")
        public void URL_IS_NULL() {

        }
    }

}