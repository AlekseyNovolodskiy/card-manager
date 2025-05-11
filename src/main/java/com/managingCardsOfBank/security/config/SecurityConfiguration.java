package com.managingCardsOfBank.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableGlobalMethodSecurity(
        prePostEnabled = true,  // включает @PreAuthorize и @PostAuthorize
        securedEnabled = true,  // включает @Secured
        jsr250Enabled = true   // включает @RolesAllowed (JSR-250)
)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    private final JwtAuthentificationFilter jwtAuthFilter;
    private final AuthenticationProvider authonticationProvider;
    private final UserDetailsService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authonticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//return http.build();

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailService)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
