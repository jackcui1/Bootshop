package com.bootshop;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username,password,enabled from user where username=?")
			.authoritiesByUsernameQuery("select username,role from role where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//http.csrf().disable();
		http.authorizeRequests()
			
			//HomePage and other page guest can access.
			.antMatchers("/").permitAll()
			.antMatchers("/static/**","/js/**","/css/**","/bootshop/**").permitAll()
			.antMatchers("/product/**").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/rest/cart/**").permitAll()
			.antMatchers("/rest/get/**").permitAll()
			.antMatchers("/cart/**").permitAll()
			.antMatchers("/welcome/**").permitAll()
			
			//Web flow role
			.antMatchers("/checkout/**").permitAll()
			.antMatchers("/order/**").permitAll()
			
			//paypal test
			.antMatchers("/pay/**").permitAll()
			
			//role for customer
			.antMatchers("/customer/**").hasRole("USER")
			
			//role for administrator
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/upload-dir/**").hasRole("ADMIN")
			
			.anyRequest().denyAll()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.rememberMe().tokenValiditySeconds(2419200)
			.and()
			.logout().permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/");
		//http.exceptionHandling().accessDeniedPage("/403");
		
	}
}