package com.taskManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.ErrorReportConfiguration.Builder;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	Configure SecurityFilterChain bean
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		
		.csrf(AbstractHttpConfigurer::disable)
		
		.authorizeHttpRequests((authorize)->
		authorize.requestMatchers("/register/**").permitAll()
		.requestMatchers("/index").permitAll()
		.requestMatchers("/users").hasRole("ADMIN")
		.anyRequest().authenticated())
		
		.formLogin(login->login
				.loginPage("/login").usernameParameter("email")
				.loginProcessingUrl("/authenticate")
				.defaultSuccessUrl("/tasks")
				.permitAll())
		.logout(
				
				logout->logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.permitAll()
				
				);
		
		return http.build();
		
	}
		
		@Autowired
		public void  configureGlobal(AuthenticationManagerBuilder builder)throws Exception{
			builder.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
		

		

	}
}
