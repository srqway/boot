package idv.hsiehpinghan.springbootstartersecurityboot.configuration;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ConfigurationEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.ResourceEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.RoleEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.UserEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.service.ConfigurationService;
import idv.hsiehpinghan.springbootstartersecurityboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final int SIZE = 3;
	// @Autowired
	// @Qualifier("mySecurityInterceptor")
	// private Filter mySecurityInterceptor;
	@Autowired
	@Qualifier("myUserDetailsService")
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigurationService configurationService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// httpSecurity.addFilterAfter(mySecurityInterceptor,
		// FilterSecurityInterceptor.class);

		// httpSecurity
		// .csrf().disable()
		// .authorizeRequests()
		// .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
		// .and()
		// .formLogin()
		// .loginProcessingUrl("/loginProcessingUrl")
		// .loginPage("/common/loginPage")
		// .failureUrl("/common/loginFailPage")
		// .usernameParameter("username")
		// .passwordParameter("password")
		// .and()
		// .logout()
		// .logoutUrl("/logoutUrl")
		// .logoutSuccessUrl("/common/logoutPage").permitAll();

		// httpSecurity
		// .formLogin().disable()
		// .anonymous().disable()
		// .httpBasic()
		// .and()
		// .requestMatchers().antMatchers("/oauth/check_token")
		// .and()
		// .authorizeRequests().anyRequest().denyAll();

		// httpSecurity.formLogin().disable();
		// httpSecurity.anonymous().disable();
		// httpSecurity.httpBasic();
		httpSecurity.csrf().disable();
//		 configureAuthorizeRequests(httpSecurity);
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
			UserEntity user = generateUserEntity("user", new String[] { "ROLE_USER" });
			userService.save(user);
		}
		if (userService.findOne("admin") == null) {
			UserEntity admin = generateUserEntity("admin", new String[] { "ROLE_ADMIN" });
			userService.save(admin);
		}
	}

	private UserEntity generateUserEntity(String username, String[] roleIds) {
		String password = username;
		Boolean enabled = true;
		Boolean accountNonExpired = true;
		Boolean credentialsNonExpired = true;
		Boolean accountNonLocked = true;
		Collection<RoleEntity> roles = generateRoleEntities(roleIds);
		UserEntity entity = new UserEntity(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, roles);
		return entity;
	}

	private Collection<RoleEntity> generateRoleEntities(String[] roleIds) {
		Collection<RoleEntity> entities = new HashSet<>(roleIds.length);
		for (String roleId : roleIds) {
			RoleEntity entity = generateRoleEntity(roleId);
			entities.add(entity);
		}
		return entities;
	}

	private RoleEntity generateRoleEntity(String roleId) {
		String rolename = "role name";
		Collection<ResourceEntity> resources = generateResourceEntities();
		RoleEntity entity = new RoleEntity(roleId, rolename, resources);
		return entity;

	}

	private Collection<ResourceEntity> generateResourceEntities() {
		Collection<ResourceEntity> entities = new HashSet<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			Integer order = i;
			String path = String.format("/user/path_%d", i);
			ResourceEntity entity = generateResourceEntity(order, path);
			entities.add(entity);
		}
		return entities;
	}

	private ResourceEntity generateResourceEntity(Integer order, String path) {
		ResourceEntity entity = new ResourceEntity(order, path);
		return entity;
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
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = httpSecurity
				.authorizeRequests();
//		 expressionInterceptUrlRegistry.antMatchers("/user/**").hasAnyRole("USER",
//		 "ADMIN");
//		expressionInterceptUrlRegistry.antMatchers("/**").permitAll();
	}
}
