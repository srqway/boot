package idv.hsiehpinghan.springsecurityoauth2boot.configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import idv.hsiehpinghan.springsecurityoauth2boot.entity.ClientEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.entity.GrantedAuthorityEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.service.ClientService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerSpringConfiguration extends AuthorizationServerConfigurerAdapter {
	private static final String CLIENT_ID = "client_Id";
	private static final String RESOURCE_ID_0 = "order";
	private static final String RESOURCE_ID_1 = "product";
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private ClientService clientService;
	
	@PostConstruct
	public void postConstruct() {
		
		http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html
		http://blog.didispace.com/spring-security-oauth2-xjf-1/
		http://www.baeldung.com/rest-api-spring-oauth2-angularjs
		http://www.baeldung.com/sso-spring-security-oauth2
		https://developers.douban.com/wiki/?title=oauth2
			https://github.com/pzxwhc/MineKnowContainer/issues/59
				http://blog.csdn.net/buyaore_wo/article/details/48680981
			
//		public static final String AUTHORIZATION_CODE = "authorization_code";
//		public static final String IMPLICIT = "implicit";
//		public static final String RESOURCE_OWNER_PASSWORD_CREDENTIALS = "resource_owner_password_credentials";
//		public static final String CLIENT_CREDENTIALS = "client_credentials";
		
		if (clientService.findOne("client_credentials_client_id") == null) {
			String clientId = "client_credentials_client_id";;
			String clientSecret = "client_secret";
			Set<String> scope = Stream.of("select").collect(Collectors.toSet());
			Set<String> resourceIds,
			Set<String> authorizedGrantTypes, Set<String> registeredRedirectUris, Set<String> autoApproveScopes,
			Set<GrantedAuthorityEntity> authorities, Integer accessTokenValiditySeconds,
			Integer refreshTokenValiditySeconds, Map<String, String> additionalInformation
			ClientEntity ClientEntity = new ClientEntity(clientId, clientSecret, scope, resourceIds, authorizedGrantTypes, registeredRedirectUris, autoApproveScopes, authorities, accessTokenValiditySeconds, refreshTokenValiditySeconds, additionalInformation);
		}
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
		clientDetailsServiceConfigurer.withClientDetails(clientDetailsService);
//		clientDetailsServiceConfigurer.withClientDetails(clientDetailsService).jdbc(dataSource).withClient(CLIENT_ID).resourceIds(RESOURCE_ID_0, RESOURCE_ID_1)
//		.authorizedGrantTypes("client_credentials", "refresh_token").scopes("select").authorities("client").secret("123456");
		
		
//		clientDetailsServiceConfigurer.inMemory().withClient("client_1").resourceIds(DEMO_RESOURCE_ID)
//				.authorizedGrantTypes("client_credentials", "refresh_token").scopes("select").authorities("client")
//				.secret("123456")
//				.and().withClient("client_2").resourceIds(DEMO_RESOURCE_ID)
//				.authorizedGrantTypes("password", "refresh_token").scopes("select").authorities("client")
//				.secret("123456");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
		authorizationServerEndpointsConfigurer.tokenStore(new RedisTokenStore(redisConnectionFactory)).authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
		authorizationServerSecurityConfigurer.allowFormAuthenticationForClients();
	}
}
