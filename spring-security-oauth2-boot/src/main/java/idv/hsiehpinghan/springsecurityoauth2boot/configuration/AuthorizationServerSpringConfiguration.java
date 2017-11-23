package idv.hsiehpinghan.springsecurityoauth2boot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerSpringConfiguration extends AuthorizationServerConfigurerAdapter {

    private int accessTokenValiditySeconds = 10000;
    private int refreshTokenValiditySeconds = 30000;

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
//    	authorizationServerEndpointsConfigurer
//                .authenticationManager(this.authenticationManager);
//    }
    /**
     * Configure the non-security features of the Authorization Server endpoints, like token store, token customizations, user approvals and grant types.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }
    
    /**
     * Configure the security of the Authorization Server, which means in practical terms the /oauth/token endpoint.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
    	authorizationServerSecurityConfigurer
                // we're allowing access to the token only for clients with 'ROLE_TRUSTED_CLIENT' authority
                .tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    /**
     * Configure the ClientDetailsService, declaring individual clients and their properties.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
    	clientDetailsServiceConfigurer.inMemory()
                .withClient("trusted-app")
                    .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                    .authorities("ROLE_TRUSTED_CLIENT")
                    .scopes("read", "write")
                    .resourceIds(resourceId)
                    .accessTokenValiditySeconds(accessTokenValiditySeconds)
                    .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
                    .secret("secret");
    }
    
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    
//    @Bean
//    private JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("abcd");
//        return converter;
//    }
//    
//    
//    @Bean
    private JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        new ClassPathResource("keystore.jks"),
                        "mypass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeys"));
        return converter;
    }
}

//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerSpringConfiguration extends AuthorizationServerConfigurerAdapter {

//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient("service-account-1").secret("service-account-1-secret")
//				.authorizedGrantTypes("client_credentials").scopes("resource-server-read", "resource-server-write");
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		// 允许表单认证
//		oauthServer.allowFormAuthenticationForClients();
//	}
//}

//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerSpringConfiguration extends AuthorizationServerConfigurerAdapter {
//	private static final String DEMO_RESOURCE_ID = "order";
//	
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //配置两个客户端,一个用于password认证一个用于client认证
//        clients.inMemory().withClient("client_1")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                .scopes("select")
//                .authorities("client")
//                .secret("123456")
//                .and().withClient("client_2")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("select")
//                .authorities("client")
//                .secret("123456");
//    }
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .tokenStore(new RedisTokenStore(redisConnectionFactory))
//                .authenticationManager(authenticationManager);
//    }
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        //允许表单认证
//        oauthServer.allowFormAuthenticationForClients();
//    }
//}

//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerSpringConfiguration extends AuthorizationServerConfigurerAdapter {
//	private static final String CLIENT_ID = "client_Id";
//	private static final String RESOURCE_ID_0 = "order";
//	private static final String RESOURCE_ID_1 = "product";
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private RedisConnectionFactory redisConnectionFactory;
////	@Autowired
////	private DataSource dataSource;
//	@Autowired
//	private ClientDetailsService clientDetailsService;
//	@Autowired
//	private ClientService clientService;
//	
//	@PostConstruct
//	public void postConstruct() {
//		
////		http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html
////		http://blog.didispace.com/spring-security-oauth2-xjf-1/
////		http://www.baeldung.com/rest-api-spring-oauth2-angularjs
////		http://www.baeldung.com/sso-spring-security-oauth2
////		https://developers.douban.com/wiki/?title=oauth2
////		https://github.com/pzxwhc/MineKnowContainer/issues/59
////		http://blog.csdn.net/buyaore_wo/article/details/48680981
//			
////		public static final String AUTHORIZATION_CODE = "authorization_code";
////		public static final String IMPLICIT = "implicit";
////		public static final String RESOURCE_OWNER_PASSWORD_CREDENTIALS = "resource_owner_password_credentials";
////		public static final String CLIENT_CREDENTIALS = "client_credentials";
//		
//		if (clientService.findOne("authorization_code_client_id") == null) {
//			// TODO
//		}
//		if (clientService.findOne("implicit_client_id") == null) {
//			// TODO
//		}
//		if (clientService.findOne("resource_owner_password_credentials_client_id") == null) {
//			// TODO
//		}
//		if (clientService.findOne("client_credentials_client_id") == null) {
//			String clientId = "client_credentials_client_id";;
//			String clientSecret = "123456";
//			Set<String> scope = Stream.of("select").collect(Collectors.toSet());
//			Set<String> resourceIds = Stream.of("order").collect(Collectors.toSet());
//			Set<String> authorizedGrantTypes = Stream.of("client_credentials", "refresh_token").collect(Collectors.toSet());
//			Set<String> registeredRedirectUris = null;
//			Set<String> autoApproveScopes = null;
//			Set<GrantedAuthorityEntity> authorities =  Stream.of(new GrantedAuthorityEntity("client")).collect(Collectors.toSet());
//			Integer accessTokenValiditySeconds = null;
//			Integer refreshTokenValiditySeconds = null;
//			Map<String, String> additionalInformation = null;
//			ClientEntity clientEntity = new ClientEntity(clientId, clientSecret, scope, resourceIds, authorizedGrantTypes, registeredRedirectUris, autoApproveScopes, authorities, accessTokenValiditySeconds, refreshTokenValiditySeconds, additionalInformation);
//			clientService.save(clientEntity);
//		}
//	}
//	
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
//		clientDetailsServiceConfigurer.withClientDetails(clientDetailsService);
////		clientDetailsServiceConfigurer.jdbc(null).withClient(CLIENT_ID).resourceIds(RESOURCE_ID_0, RESOURCE_ID_1)
////		.authorizedGrantTypes("client_credentials", "refresh_token").scopes("select").authorities("client").secret("123456");
//		
//		
////		clientDetailsServiceConfigurer.inMemory().withClient("client_1").resourceIds(DEMO_RESOURCE_ID)
////				.authorizedGrantTypes("client_credentials", "refresh_token").scopes("select").authorities("client")
////				.secret("123456")
////				.and().withClient("client_2").resourceIds(DEMO_RESOURCE_ID)
////				.authorizedGrantTypes("password", "refresh_token").scopes("select").authorities("client")
////				.secret("123456");
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
//		authorizationServerEndpointsConfigurer.tokenStore(new RedisTokenStore(redisConnectionFactory)).authenticationManager(authenticationManager);
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
//		authorizationServerSecurityConfigurer.allowFormAuthenticationForClients();
//	}
//}
