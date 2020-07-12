package com.authorbook.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.authorbook.api.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	 	
		auth
				.userDetailsService(userDetailService());
				 
	}
	@Override
	protected void configure(HttpSecurity http)throws Exception{
  
		http
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/author/**").hasRole("AUTHOR")
		.antMatchers("/book/**").hasRole("ADMIN")
		.and()
		.httpBasic();
 
		/*
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/rest/**").fullyAuthenticated().and().httpBasic();
		 */
	}
	@Bean
	PasswordEncoder passwordEncoder() {
 
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailService() {
		return new UserDetailsServiceImpl();
	}
}
