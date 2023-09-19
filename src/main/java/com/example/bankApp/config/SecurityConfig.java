package com.example.bankApp.config;

import com.example.bankApp.entity.UserInfoService;
import com.example.bankApp.jwt.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;

    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/user/**").hasAuthority("user")
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/manager/**").hasAuthority("manager")
                .and()
                .authorizeHttpRequests().requestMatchers("/account/user/**").hasAuthority("user")
                .and()
                .authorizeHttpRequests().requestMatchers("/account/manager/**").hasAuthority("manager")
                .and()
                .authorizeHttpRequests().requestMatchers("/manager-account/user/**").hasAuthority("user")
                .and()
                .authorizeHttpRequests().requestMatchers("/manager-account/manager/**").hasAuthority("manager")
                .and()
                .authorizeHttpRequests().requestMatchers("/client/user/**").hasAuthority("user")
                .and()
                .authorizeHttpRequests().requestMatchers("/client/manager/**").hasAuthority("manager")
                .and()
                .authorizeHttpRequests().requestMatchers("/transaction/user/**").hasAuthority("user")
                .and()
                .authorizeHttpRequests().requestMatchers("/transaction/manager/**").hasAuthority("manager")
                .and()
                .authorizeHttpRequests().requestMatchers("/agreement/user/**").hasAuthority("user")
                .and()
                .authorizeHttpRequests().requestMatchers("/agreement/manager/**").hasAuthority("manager")
                .and()
                .authorizeHttpRequests().requestMatchers("/product/user/**").hasAuthority("user")
                .and()
                .authorizeHttpRequests().requestMatchers("/product/manager/**").hasAuthority("manager")
                .and()
//                .authorizeHttpRequests().requestMatchers("/**").hasAuthority("admin")
//                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }





    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}

