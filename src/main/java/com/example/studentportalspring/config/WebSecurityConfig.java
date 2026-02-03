package com.example.studentportalspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/", "/login", "/loginPage", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/loginPage")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

////    @Bean
////    DaoAuthenticationProvider authenticationProvider(UserDetailsService uds, PasswordEncoder encoder) {
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(uds);
////        provider.setPasswordEncoder(encoder);
////        return provider;
////    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
