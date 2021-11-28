package br.com.rd.MestreDasFacas.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenConfig {

    @Value("${token.password}")
    public void setTokenPassword (String tokenPassword){
        JWTAuthenticateFilter.TOKEN_PASSWORD = tokenPassword;
    }

    @Value("${token.expires}")
    public void setTokenExpires (int tokenExpires){
        JWTAuthenticateFilter.TOKEN_EXPIRES = tokenExpires;
    }
}
