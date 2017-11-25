package idv.hsiehpinghan.springsecurityoauth2boot.redis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;

@Configuration
@EnableResourceServer
@Profile(Constant.REDIS_TOKEN_STORE_RESOURCE_SERVER_PROFILE)
public class ResourceServerSpringConfiguration extends ResourceServerConfigurerAdapter {
	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId).stateless(true);
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

	@Bean
	public RemoteTokenServices remoteTokenServices(final @Value("${auth.server.url}") String checkTokenUrl,
			final @Value("${auth.server.clientId}") String clientId,
			final @Value("${auth.server.clientsecret}") String clientSecret) {
		
		DefaultResponseErrorHandler a;
		
		final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
		remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
		remoteTokenServices.setClientId(clientId);
		remoteTokenServices.setClientSecret(clientSecret);
		AccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
		return remoteTokenServices;
	}
}
