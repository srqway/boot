package idv.hsiehpinghan.springsecurityoauth2boot.jwt.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;
import idv.hsiehpinghan.springsecurityoauth2boot.jwt.entity.RoleEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.jwt.entity.UserEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.jwt.service.UserService;

@Configuration
@EnableWebSecurity(debug = true)
@Profile(Constant.JWT_TOKEN_STORE_AUTHORIZATION_SERVER_PROFILE)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecuritySpringConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;

	@PostConstruct
	public void postConstruct() {
		// @formatter:off
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
		if (userService.findOne("other") == null) {
			UserEntity admin = new UserEntity("other", "other", true, true, true, true,
					Arrays.asList(new RoleEntity("ROLE_OTHER", "other role name", null)));
			userService.save(admin);
		}
		// @formatter:on
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// @formatter:off
		httpSecurity
			.formLogin().disable()
			.anonymous().disable()
			.httpBasic()
			.and()
			.authorizeRequests()
				.anyRequest().denyAll();
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
