package com.supply.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig {
	
	@Bean
    PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();

	} // funcion que permite encriptar password

}
