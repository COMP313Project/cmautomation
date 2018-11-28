package com.cmautomation.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class CMAppSecurityConfig extends WebSecurityConfigurerAdapter {
	//add a reference to the security data source
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		//.antMatchers("/").access("hasRole('USER')")
		.antMatchers("/").hasAnyRole("CMA","QA","TSA","ADMIN")// to excludse 'user' role
		.antMatchers("/cma/**").hasAnyRole("CMA","QA","TSA")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/vendor/**").hasRole("ADMIN")
		.antMatchers("/environment/**").hasRole("ADMIN")
		.antMatchers("/qa/**").hasAnyRole("CMA","QA","TSA")
		.antMatchers("/tsa/**").hasAnyRole("CMA","QA","TSA")
		.and()
			.formLogin()
			.loginPage("/showLoginPage")
			.loginProcessingUrl("/authenticateTheUser").permitAll()
			.and().logout().permitAll()
			.and().exceptionHandling().accessDeniedPage("/access-denied");
	}

}
