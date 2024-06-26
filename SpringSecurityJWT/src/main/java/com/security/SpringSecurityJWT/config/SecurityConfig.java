package com.security.SpringSecurityJWT.config;

import com.security.SpringSecurityJWT.service.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

    //yeet

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    //Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**","/public/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("USER")
                        .requestMatchers("/adminuser/**").hasAnyAuthority("USER","ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //SessionCreationPolicy POLICY MEANS
                //ALWAYS
                //Always create an HttpSession
                //IF_REQUIRED
                //Spring Security will only create an HttpSession if required
                //NEVER
                //Spring Security will never create an HttpSession, but will use the HttpSession if it already exists
                //STATELESS
                //Spring Security will never create an HttpSession and it will never use it to obtain the SecurityContext
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class //Processes an authentication form submission.
                );
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
