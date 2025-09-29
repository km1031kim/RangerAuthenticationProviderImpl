package org.example;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.sasl.AuthenticationException;


public class RangerAuthenticationProviderImpl implements PasswdAuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(RangerAuthenticationProviderImpl.class);

    private final static String RANGER_REST_URL_CONF_KEY = "ranger.plugin.hive.policy.rest.url";
    private static final String XUSERS_API = "/service/xusers/users";
    private final String rangerUrl;
    private final RestAuthService restAuthService;


    public RangerAuthenticationProviderImpl() {
        HiveConf conf = new HiveConf();
        this.rangerUrl = conf.get(RANGER_REST_URL_CONF_KEY) + XUSERS_API;
        this.restAuthService = new RestAuthService(rangerUrl);
    }


    // 테스트 편의를 위한 생성자
    public RangerAuthenticationProviderImpl(String rangerUrl, RestAuthService restAuthService) {
        this.rangerUrl = rangerUrl;
        this.restAuthService = restAuthService;
    }

    @Override
    public void Authenticate(String username, String password) throws AuthenticationException {

        log.info("Get Ranger Admin URL from HiveConf. " + RANGER_REST_URL_CONF_KEY + " = {}", rangerUrl);

        if (!restAuthService.authenticate(username, password)) {
            throw new AuthenticationException("Authentication is failed");
        }

        log.info("Authentication is finished");
    }
}
