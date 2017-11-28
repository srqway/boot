package idv.hsiehpinghan.springbootstartersecurityboot.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.RoleEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.UserEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// @formatter:off
		httpSecurity
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
			.and()
			.formLogin()
				.loginProcessingUrl("/loginProcessingUrl")
				.loginPage("/common/loginPage")
				.failureUrl("/common/loginFailPage")
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutUrl("/logoutUrl")
				.logoutSuccessUrl("/common/logoutPage").permitAll();
		// @formatter:on

		
		// for restful service
//		httpSecurity.csrf().disable();
//		
//		httpSecurity.authorizeRequests()
//		.antMatchers("/common/**").permitAll()
//		.antMatchers("/crud/**").permitAll()
//		.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//		.antMatchers("/admin/**").hasRole("ADMIN")
//		.antMatchers("/actuator/**").hasRole("ADMIN")
//		.and().formLogin().loginProcessingUrl("/loginProcessingUrl")
//				.loginPage("/common/loginPage").failureUrl("/common/loginFailPage").usernameParameter("username")
//				.passwordParameter("password").and().logout().logoutUrl("/logoutUrl")
//				.logoutSuccessUrl("/common/logoutPage").permitAll();
	}

	@PostConstruct
	public void postConstruct() {
		if (userService.findOne("user") == null) {
			UserEntity user = new UserEntity("user", "user", true, true, true, true,
					Arrays.asList(new RoleEntity("ROLE_USER", "user role name", null)));
			userService.save(user);
		}
		if (userService.findOne("admin") == null) {
			UserEntity admin = new UserEntity("admin", "admin", true, true, true, true,
					Arrays.asList(new RoleEntity("ROLE_ADMIN", "admin role name", null)));
			userService.save(admin);
		}
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService);
	}
}
