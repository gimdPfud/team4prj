package com.example.project4team.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth->{
            auth.requestMatchers("/assets/**", "/css/**", "/js/**").permitAll();
            auth.anyRequest().permitAll();
        });

        http.formLogin(login -> {
            login.loginPage("/login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/main")
                    .failureUrl("/login");
        });

        http.logout(logout -> {
            logout.logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/login");
        });

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
