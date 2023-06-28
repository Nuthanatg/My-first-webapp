package com.nuthana.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
//To save username and password we use LDAP or Database
	// here we are making use of IN Memory

//	InMemoryUserDetailsManager
//	InMemoryUserDetailsManager(UserDetails... users)
//	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		UserDetails userDetails = createNewUser("Nuthana T G", "nuthana");
		UserDetails userDetails1 = createNewUser("Meghana T G", "megha");

		return new InMemoryUserDetailsManager(userDetails, userDetails1);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

		UserDetails userDetails = User.builder() // User.withDefaultPasswordEncoder() this is depricated so we are using
													// custom password encoder
				.passwordEncoder(passwordEncoder).username(username).password(password).roles("USER", "ADMIN").build();
		return userDetails;
	}

	// to replace the depricated function in above here we are using Custom Encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// we have 2 features
	// All URLS are protected
	// A login form is shown for unauthorized requests
	// to connect with h2 we need to disable CSRF disable
	// and h2 also make use something called
	// Frames
	// we need to configrue all this

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
}
