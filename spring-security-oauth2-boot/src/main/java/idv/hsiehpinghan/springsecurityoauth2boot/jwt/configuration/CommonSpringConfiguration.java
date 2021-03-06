package idv.hsiehpinghan.springsecurityoauth2boot.jwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;

@Configuration
@Profile({ Constant.JWT_TOKEN_STORE_AUTHORIZATION_SERVER_PROFILE, Constant.JWT_TOKEN_STORE_RESOURCE_SERVER_PROFILE })
public class CommonSpringConfiguration extends AuthorizationServerConfigurerAdapter {

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		TokenStore tokenStore = generateTokenStore();
		defaultTokenServices.setTokenStore(tokenStore);
		defaultTokenServices.setSupportRefreshToken(true);
		TokenEnhancer tokenEnhancer = generateJwtAccessTokenConverter();
		defaultTokenServices.setTokenEnhancer(tokenEnhancer);
		return defaultTokenServices;
	}

	private TokenStore generateTokenStore() {
		JwtAccessTokenConverter jwtAccessTokenConverter = generateJwtAccessTokenConverter();
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	private JwtAccessTokenConverter generateJwtAccessTokenConverter() {
		ClassPathResource classPathResource = new ClassPathResource("my_keystore.jks");
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, "my_pass".toCharArray());
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("my_keypair"));
		return jwtAccessTokenConverter;
	}

}
