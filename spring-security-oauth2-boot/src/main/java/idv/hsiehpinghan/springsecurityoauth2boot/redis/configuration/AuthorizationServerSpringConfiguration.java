package idv.hsiehpinghan.springsecurityoauth2boot.redis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;
import idv.hsiehpinghan.springsecurityoauth2boot.redis.service.MyUserDetailsService;

@Configuration
@EnableAuthorizationServer
@Profile(Constant.REDIS_TOKEN_STORE_AUTHORIZATION_SERVER_PROFILE)
public class AuthorizationServerSpringConfiguration extends AuthorizationServerConfigurerAdapter {

	private int accessTokenValiditySeconds = 10000;
	private int refreshTokenValiditySeconds = 30000;

	@Value("${security.oauth2.resource.id}")
	private String resourceId;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Autowired
	private MyUserDetailsService userDetailsService;

	/**
	 * Configure the non-security features of the Authorization Server endpoints,
	 * like token store, token customizations, user approvals and grant types.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory)).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	/**
	 * Configure the security of the Authorization Server, which means in practical
	 * terms the /oauth/token endpoint.
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer)
			throws Exception {
		authorizationServerSecurityConfigurer.tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
				.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')").allowFormAuthenticationForClients();
	}

	/**
	 * Configure the ClientDetailsService, declaring individual clients and their
	 * properties.
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
		clientDetailsServiceConfigurer.inMemory().withClient("trusted-app")
				.authorizedGrantTypes("client_credentials", "password", "refresh_token")
				.authorities("ROLE_TRUSTED_CLIENT").scopes("read", "write").resourceIds(resourceId)
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
				.refreshTokenValiditySeconds(refreshTokenValiditySeconds).secret("secret");
	}

}
