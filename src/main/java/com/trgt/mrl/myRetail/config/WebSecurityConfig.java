package com.trgt.mrl.myRetail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.trgt.mrl.myRetail.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint entryPoint;

	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic().and().csrf().disable();
		http.authorizeRequests()
				.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", 
						"/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagge‌​r-ui.html")
				.permitAll()
				.antMatchers("/products")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.httpBasic()
				.authenticationEntryPoint(entryPoint)
			.and()
				.csrf()
				.disable();
		
			

		
		  /*http.authorizeRequests() 
		  		.antMatchers("/products").permitAll()
		  		.antMatchers("/products/*").authenticated()
		  	.and()
		  		.csrf()
		  		.disable();*/
		 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.config.annotation.authentication.configurers
	 * .GlobalAuthenticationConfigurerAdapter#configure(org.springframework.
	 * security.config.annotation.authentication.builders.
	 * AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());;
	}
	
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("rohit").password("rohit").roles(USER)
			.and()
				.withUser("monika").password("monika").roles(USER,ADMIN);
		
	}*/
	
	/**
	 * @return
	 */
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
