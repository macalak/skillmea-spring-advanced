package ite.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration	
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    // TODO-5:
    // Try Authorization using matchers, http.authorizeHttpRequests(authorize -> authorize
    //.requestMatchers("/api/*").hasAuthority("ROLE_USER")
    //.requestMatchers("/api/*").hasRole("ROLE_ADMIN")

    // TODO-3: Configure SecurityFilterChain with the KeycloakJwtAuthenticationConverter

}
