package com.meleia.meleia.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Mudar para IF alguma coisa
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/registro").permitAll()
                        .requestMatchers(HttpMethod.GET,"/livro/**").permitAll()

                        .requestMatchers(HttpMethod.GET,"/editora/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/editora/**").permitAll()

                        .requestMatchers(HttpMethod.GET,"/autor/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/autor/**").permitAll()

                        .requestMatchers(HttpMethod.GET,"/categoria/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/categoria/**").permitAll()



                        .requestMatchers(HttpMethod.POST,"/usuario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/usuario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/usuario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/produto").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Frontend
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config); // Aplica a configuração a todos os endpoints
        return new CorsFilter(source);
    }
}

