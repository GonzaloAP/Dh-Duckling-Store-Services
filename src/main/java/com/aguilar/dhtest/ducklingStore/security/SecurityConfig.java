package com.aguilar.dhtest.ducklingStore.security;

import com.aguilar.dhtest.ducklingStore.security.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    String[] whiteList = new String[]{"/dh/**", "/swagger-ui/**"};

    @Bean
    CorsFilter corsFilter() {
        return new CorsFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers()
                .cacheControl()
                .and()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'; style-src 'self'; form-action 'self'")
                .and()
                .httpStrictTransportSecurity()
                .maxAgeInSeconds(3628800)
                .includeSubDomains(true)
                .preload(false);


        http.formLogin().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // .antMatchers(whiteList).permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(corsFilter(), SessionManagementFilter.class);

    }

}
