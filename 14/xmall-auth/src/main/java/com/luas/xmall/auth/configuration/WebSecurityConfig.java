package com.luas.xmall.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .anyRequest()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "userDetailsService")
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return createUserDetailsService();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return createUserDetailsService();
    }

    private UserDetailsService createUserDetailsService() {
        String password = passwordEncoder().encode("123456");

        List<UserDetails> users = new ArrayList<>();

        UserDetails user_admin = User.withUsername("admin").password(password).authorities("ADMIN", "USER").build();
        UserDetails user_1 = User.withUsername("user_1").password(password).authorities("ADMIN", "USER").build();
        UserDetails user_2 = User.withUsername("user_2").password(password).authorities("USER").build();

        users.add(user_admin);
        users.add(user_1);
        users.add(user_2);

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
