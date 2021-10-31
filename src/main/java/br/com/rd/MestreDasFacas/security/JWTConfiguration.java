package br.com.rd.MestreDasFacas.security;

import br.com.rd.MestreDasFacas.service.CustomerUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomerUserServiceImpl customerUserService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/customers/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/customers/**").authenticated()
                .antMatchers("/telephones/**").authenticated()
                .antMatchers("/creditCards/**").authenticated()
                .antMatchers("/addresses/**").authenticated()
                .antMatchers("/").permitAll()
                .and()
                .addFilter(new JWTAuthenticateFilter(authenticationManager()))
                .addFilter(new JWTValidateFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //sem estado gravado no servidor
    }

    //opcional, habilita requisicoes cross origin
//    @Bean
//    CorsConfigurationSource corsConfiguration(){
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }
}
