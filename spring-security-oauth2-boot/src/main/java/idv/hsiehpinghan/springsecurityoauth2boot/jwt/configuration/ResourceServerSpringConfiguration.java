package idv.hsiehpinghan.springsecurityoauth2boot.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;

@Configuration
@EnableResourceServer
@Profile(Constant.JWT_TOKEN_STORE_RESOURCE_SERVER_PROFILE)
public class ResourceServerSpringConfiguration extends ResourceServerConfigurerAdapter {
	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId).tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		// @formatter:off
		httpSecurity
			.antMatcher("/api/**")
				.csrf().disable()
				.anonymous().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/api/userMethod").access("hasAnyRole('USER')")
				.antMatchers("/api/adminMethod").hasRole("ADMIN")
				.antMatchers("/api/**").authenticated();
		// @formatter:on
	}

}
