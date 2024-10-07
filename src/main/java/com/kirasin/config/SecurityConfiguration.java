package com.kirasin.config;

import com.kirasin.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .requestMatchers("/static/**", "/login", "/registration", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/customers").hasAuthority(Role.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .formLogin(login -> login
                        .loginPage("/login").usernameParameter("email")
                        .defaultSuccessUrl("/products"))
                .csrf(AbstractHttpConfigurer::disable); // Disable CSRF for simplicity, enable it in production
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
