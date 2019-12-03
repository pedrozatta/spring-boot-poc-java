package br.com.jasp.example.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthentiationProvider customAuthProvider;

    @Autowired
    protected AuthenticationFilter demoAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // everyone is allowed tp view login page
        http.authorizeRequests().antMatchers("**").authenticated();
        http.addFilterAfter(demoAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobalSecurity (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthProvider);
    }


}