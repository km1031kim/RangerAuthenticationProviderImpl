package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.sasl.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
class RangerAuthenticationProviderImplTest {

    @Mock
    private RestAuthService restAuthService;

    @InjectMocks
    private RangerAuthenticationProviderImpl authenticationProvider;


    @Test
    @DisplayName("인증 성공")
    void AUTH_OK() throws AuthenticationException {

        // given & when
        Mockito.when(restAuthService.authenticate(anyString(), anyString())).thenReturn(true);

        // then
        assertDoesNotThrow(() -> authenticationProvider.Authenticate("dummyUsername", "dummyPassword"));
    }

    @Test
    @DisplayName("인증 실패")
    void AUTH_FAILED() throws AuthenticationException {

        // given & when
        Mockito.when(restAuthService.authenticate(any(), any())).thenReturn(false);

        // then
        assertThrows(AuthenticationException.class, () -> authenticationProvider.Authenticate("dummyUsername", "dummyPassword"));
    }


}