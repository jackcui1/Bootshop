package com.bootshop;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//@Configuration
///@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	DataSource dataSource;
//	
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth)throws Exception{
//		auth.jdbcAuthentication().dataSource(dataSource)
//			.usersByUsernameQuery("select username,password,enabled from user where username=?")
//			.authoritiesByUsernameQuery("select username,role from role where username=?");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		//http.csrf().disable();
//		http.authorizeRequests()
//			
//			.antMatchers("/").permitAll()
//			.antMatchers("/static/**","/js/**","/css/**","/bootshop/**").permitAll()
//			.antMatchers("/product/**").permitAll()
//			
//			//these is for test
//			.antMatchers("/welcome/**").permitAll()
//			.antMatchers("/thankYou/**").permitAll()
//			.antMatchers("/toOrder/**").permitAll()
//			.antMatchers("/order/**").permitAll()
//			.antMatchers("/registration/**").permitAll()
//			//Test end.
//			
//			.antMatchers("/welcome/**").permitAll()
//			.antMatchers("/rest/cart/**").permitAll()
//			.antMatchers("/cart/**").permitAll()
//			.antMatchers("/customer/**").hasRole("USER")
//			.antMatchers("/rest/get/**").permitAll()
//			.antMatchers("/admin/**").hasRole("ADMIN")
//			.antMatchers("/admin/up/**").hasRole("ADMIN")
//			.antMatchers("/upload-dir/**").hasRole("ADMIN")
//			.anyRequest().denyAll()
//			.and()
//			.formLogin().loginPage("/login").permitAll()
//			.and()
//			.rememberMe().tokenValiditySeconds(2419200)
//			.and()
//			.logout().permitAll()
//			.and()
//			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//			.logoutSuccessUrl("/");
//		//http.exceptionHandling().accessDeniedPage("/403");
		
//	}
//}