package org.nader.io.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigForSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/users**").hasRole("ADMIN")
		.antMatchers("/users/**").hasRole("ADMIN")
		.antMatchers("/account**").hasRole("USER")
		.and()
		.formLogin().loginPage("/login")
		.and()
		.csrf().disable().logout().logoutUrl("/logout");
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService())
		.passwordEncoder(passwordencoder());

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select name,password,enabled from youser where name=?")
				.authoritiesByUsernameQuery("select youser.name, role.name from youser" 
										  + " join youser_roles on youser.id = youser_roles.yousers_id"
										  + " join role on youser_roles.roles_id = role.id" 
										  + " where youser.name = ? ");
	}
}