package com.app.config;

import com.app.repository.UserRepository;
import com.app.service.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JWTFilter jwtFilter;


    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
       http.csrf().disable().cors().disable();
        //http.authorizeHttpRequests().anyRequest().permitAll();



        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

//        http.authorizeHttpRequests()
//                .requestMatchers(
//                        "/api/v1/auth/signup"
//                        ,"/api/v1/auth/usersign"
//                        ,"/api/v1/auth/content-manager-signup"
//                        ,"/api/v1/auth/login-otp"
//                        ,"/api/v1/auth/blog-manager-signup")
//                .permitAll()
//                .requestMatchers(
//                        "/api/v1/cars/addCars"
//                )
//                .hasRole("USER")
//                .anyRequest()
//                .authenticated();
//         Authorize all requests
        http.authorizeHttpRequests().anyRequest().permitAll();

        // Use the custom authentication filter

        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
