package idv.hsiehpinghan.springsecurityoauth2boot.jwt.configuration;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.security.web.util.matcher.RequestMatcher;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;

@Configuration
@EnableResourceServer
@Profile(Constant.JWT_TOKEN_STORE_RESOURCE_SERVER_PROFILE)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
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
		// @// @formatter:off
		RequestMatcher oAuthRequestedMatcher = new OAuthRequestedMatcher();
		httpSecurity
			.requestMatcher(oAuthRequestedMatcher)
				.csrf().disable()
				.anonymous().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/api/hello").access("hasAnyRole('USER')")
				.antMatchers("/api/admin").hasRole("ADMIN")
				.antMatchers("/api/**").authenticated();
		// @formatter:on
	}

	private static class OAuthRequestedMatcher implements RequestMatcher {
		public boolean matches(HttpServletRequest request) {
			// Determine if the resource called is "/api/**"
			String path = request.getServletPath();
			if (path.length() >= 5) {
				path = path.substring(0, 5);
				boolean isApi = path.equals("/api/");
				return isApi;
			} else
				return false;
		}
	}
}
