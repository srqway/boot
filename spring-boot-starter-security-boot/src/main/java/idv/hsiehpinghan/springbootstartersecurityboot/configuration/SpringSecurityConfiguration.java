package idv.hsiehpinghan.springbootstartersecurityboot.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ConfigurationEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.RoleEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.UserEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.service.ConfigurationService;
import idv.hsiehpinghan.springbootstartersecurityboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Autowired
//	@Qualifier("mySecurityInterceptor")
//	private Filter mySecurityInterceptor;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigurationService configurationService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// httpSecurity.addFilterAfter(mySecurityInterceptor,
		// FilterSecurityInterceptor.class);

//		httpSecurity
//			.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//				.and()
//				.formLogin()
//					.loginProcessingUrl("/loginProcessingUrl")
//					.loginPage("/common/loginPage")
//					.failureUrl("/common/loginFailPage")
//					.usernameParameter("username")
//					.passwordParameter("password")
//				.and()
//				.logout()
//					.logoutUrl("/logoutUrl")
//					.logoutSuccessUrl("/common/logoutPage").permitAll();
	
		
//		httpSecurity
//		.formLogin().disable()
//		.anonymous().disable()
//		.httpBasic()
//		.and()
//		.requestMatchers().antMatchers("/oauth/check_token")
//		.and()
//		.authorizeRequests().anyRequest().denyAll();
		
//		httpSecurity.formLogin().disable();
//		httpSecurity.anonymous().disable();
//		httpSecurity.httpBasic();
		httpSecurity.csrf().disable();
//		configureAuthorizeRequests(httpSecurity);
		configureFormLogin(httpSecurity);
		configureLogout(httpSecurity);
	}
	
	@PostConstruct
	public void postConstruct() {
		generateConfigurationEntity();
		generateUserEntity();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService);
	}

	private void generateConfigurationEntity() {
		ConfigurationEntity.Id id = null;
		// formLogin
		id = new ConfigurationEntity.Id("formLogin", "loginProcessingUrl");
		if (configurationService.findOne(id) == null) {
			ConfigurationEntity entity = new ConfigurationEntity(id, "/loginProcessingUrl");
			configurationService.save(entity);
		}
		id = new ConfigurationEntity.Id("formLogin", "loginPage");
		if (configurationService.findOne(id) == null) {
			ConfigurationEntity entity = new ConfigurationEntity(id, "/common/loginPage");
			configurationService.save(entity);
		}
		id = new ConfigurationEntity.Id("formLogin", "failureUrl");
		if (configurationService.findOne(id) == null) {
			ConfigurationEntity entity = new ConfigurationEntity(id, "/common/loginFailPage");
			configurationService.save(entity);
		}
		id = new ConfigurationEntity.Id("formLogin", "usernameParameter");
		if (configurationService.findOne(id) == null) {
			ConfigurationEntity entity = new ConfigurationEntity(id, "username");
			configurationService.save(entity);
		}
		id = new ConfigurationEntity.Id("formLogin", "passwordParameter");
		if (configurationService.findOne(id) == null) {
			ConfigurationEntity entity = new ConfigurationEntity(id, "password");
			configurationService.save(entity);
		}
		// logout
		id = new ConfigurationEntity.Id("logout", "logoutUrl");
		if (configurationService.findOne(id) == null) {
			ConfigurationEntity entity = new ConfigurationEntity(id, "/logoutUrl");
			configurationService.save(entity);
		}
		id = new ConfigurationEntity.Id("logout", "logoutSuccessUrl");
		if (configurationService.findOne(id) == null) {
			ConfigurationEntity entity = new ConfigurationEntity(id, "/common/logoutPage");
			configurationService.save(entity);
		}
	}

	private void generateUserEntity() {
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


	private void configureLogout(HttpSecurity httpSecurity) throws Exception {
		ConfigurationEntity.Id id = null;
		ConfigurationEntity entity = null;
		LogoutConfigurer<HttpSecurity> logout = httpSecurity.logout();
		id = new ConfigurationEntity.Id("logout", "logoutUrl");
		entity = configurationService.findOne(id);
		if (entity != null) {
			logout.logoutUrl(entity.getValue());
		}
		id = new ConfigurationEntity.Id("logout", "logoutSuccessUrl");
		entity = configurationService.findOne(id);
		if (entity != null) {
			logout.logoutSuccessUrl(entity.getValue());
		}
	}
	
	private void configureFormLogin(HttpSecurity httpSecurity) throws Exception {
		ConfigurationEntity.Id id = null;
		ConfigurationEntity entity = null;
		FormLoginConfigurer<HttpSecurity> formLogin = httpSecurity.formLogin();
		id = new ConfigurationEntity.Id("formLogin", "loginProcessingUrl");
		entity = configurationService.findOne(id);
		if (entity != null) {
			formLogin.loginProcessingUrl(entity.getValue());
		}
		id = new ConfigurationEntity.Id("formLogin", "loginPage");
		entity = configurationService.findOne(id);
		if (entity != null) {
			formLogin.loginPage(entity.getValue());
		}
		id = new ConfigurationEntity.Id("formLogin", "failureUrl");
		entity = configurationService.findOne(id);
		if (entity != null) {
			formLogin.failureUrl(entity.getValue());
		}
		id = new ConfigurationEntity.Id("formLogin", "usernameParameter");
		entity = configurationService.findOne(id);
		if (entity != null) {
			formLogin.usernameParameter(entity.getValue());
		}
		id = new ConfigurationEntity.Id("formLogin", "passwordParameter");
		entity = configurationService.findOne(id);
		if (entity != null) {
			formLogin.passwordParameter(entity.getValue());
		}
	}
	private void configureAuthorizeRequests(HttpSecurity httpSecurity) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = httpSecurity.authorizeRequests();
//		expressionInterceptUrlRegistry.antMatchers("/user/**").hasAnyRole("USER", "ADMIN");
		expressionInterceptUrlRegistry.antMatchers("/**").permitAll();
	}
}
