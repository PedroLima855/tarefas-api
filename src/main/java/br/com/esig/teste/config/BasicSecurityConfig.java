package br.com.esig.teste.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Profile("basic-security")
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 
	        auth.inMemoryAuthentication()
	                .withUser("esig@master")
	                .password(passwordEncoder.encode("esig"))
	                .roles("USER", "ADMIN")
	                .and()
	                .withUser("esig@master.com")
	                .password(passwordEncoder.encode("master"))
	                .roles("USER");
	    }

}
