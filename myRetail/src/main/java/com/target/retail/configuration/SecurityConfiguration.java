package com.target.retail.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author Revanth
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Override
	    protected void configure(HttpSecurity http) throws Exception 
	    {
		  http
          .httpBasic()
          .and()
          .authorizeRequests()
          .anyRequest().authenticated();    // it's indicate all request will be secure
           http.csrf().disable();      
	    }
	  
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) 
	            throws Exception 
	    {
	        auth.inMemoryAuthentication()
	            .withUser("user")
	            .password("{noop}password")
	            .roles("USER");
	        
	    }
	    
	
}
