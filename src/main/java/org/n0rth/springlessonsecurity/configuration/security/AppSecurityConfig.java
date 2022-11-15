package org.n0rth.springlessonsecurity.configuration.security;


import org.n0rth.springlessonsecurity.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@EnableWebSecurity
public class AppSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/hr_info/**").
                hasRole(UserRole.HR.name()).
                antMatchers("/manager_info/**").hasRole(UserRole.MANAGER.name())
                .antMatchers("/**").hasAnyRole(Arrays.stream(UserRole.values()).
                        map(Enum::name).toArray(String[]::new))
                .and().formLogin();

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("Ivan")
                .password(passwordEncoder.encode("jacket2"))
                .roles(UserRole.HR.name())
                .build();
        UserDetails admin = User.builder()
                .username("Mashka")
                .password(passwordEncoder.encode("kakashka"))
                .roles(UserRole.MANAGER.name())
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
