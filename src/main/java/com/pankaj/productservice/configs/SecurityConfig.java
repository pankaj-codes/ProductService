package com.pankaj.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /**
     * This filter chain is needed to restrict all the requests otherwise they were not authorized by default
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    /**
     * Jwt decoder is neccessary to decode the jwt token. URL is te location from which decoder should retrieve the
     * JWKS (JSON Web Key Set)
     *
     * @return
     */

    @Bean
    public JwtDecoder getJwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:" + 6010 + "/oauth2/jwks").build();
    }
}
