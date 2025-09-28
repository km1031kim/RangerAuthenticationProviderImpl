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
    private final static String RANGER_REST_URL_CONF_KEY = "ranger.plugin.hive.policy.rest.url";
    private final String rangerUrl;
    private final RestAuthService restAuthService;


    public RangerAuthenticationProviderImpl() {
        HiveConf conf = new HiveConf();
        this.rangerUrl = conf.get(RANGER_REST_URL_CONF_KEY);
        this.restAuthService = new RestAuthService(rangerUrl);
    }

    @Override
    public void Authenticate(String username, String password) throws AuthenticationException {

        log.info("[RangerAuthenticationProviderImpl] Get Ranger Admin URL from HiveConf. " + RANGER_REST_URL_CONF_KEY + " = {}", rangerUrl);

        if (rangerUrl == null || rangerUrl.isEmpty()) {
            throw new AuthenticationException("[RangerAuthenticationProviderImpl] URL is null or empty, " + RANGER_REST_URL_CONF_KEY + " = " + rangerUrl);
        }

        if (!restAuthService.authenticate(username, password)) {
            throw new AuthenticationException("Authentication is failed");
        }
    }
}
