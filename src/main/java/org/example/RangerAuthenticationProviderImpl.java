package org.example;

import groovy.util.logging.Slf4j;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.sasl.AuthenticationException;


@Slf4j
public class RangerAuthenticationProviderImpl implements PasswdAuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(RangerAuthenticationProviderImpl.class);

    @Override
    public void Authenticate(String username, String password) throws AuthenticationException {

        log.info("[RangerAuthenticationProviderImpl] start authentication, username : {}", username);

        // conf에서 ranger url 가져오기
        HiveConf conf = new HiveConf();
        String url = conf.get("ranger.plugin.hive.policy.rest.url");

        // RestTemplate 으로 id, pwd 전달 -> 200OK -> void, 그 외는 -> AuthenticationException.


        log.info("[RangerAuthenticationProviderImpl] get ranger api url from conf, url : {}", url);

        // api 요청 날리기

        // 정상 -> void


        // 예외 -> AuthenticationException
    }
}
