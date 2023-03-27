package com.sanreg.sanatorium.config;

import com.sanreg.sanatorium.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class MySecurityConfig {
    private CustomUserDetailsService userDetailsService;
    @Autowired
    public MySecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login", "/register","/register/save","/register/activate/*").permitAll()
                .requestMatchers("/","/registration","/savePatient").hasAnyRole("PATIENT","DOCTOR")
                .requestMatchers("/findAllPatients","/updatePatient/{id}","/deletePatient/{id}","/addByDoctor").hasRole("DOCTOR")
                .and()
                .formLogin(formLogin ->
                        formLogin.loginPage("/login").defaultSuccessUrl("/").loginProcessingUrl("/login")
                        .failureUrl("/login?error=true").permitAll())
                .logout(logout->
                        logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
                .build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
