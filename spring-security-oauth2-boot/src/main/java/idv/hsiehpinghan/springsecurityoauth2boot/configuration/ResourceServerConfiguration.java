package idv.hsiehpinghan.springsecurityoauth2boot.configuration;

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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;

@Configuration
@EnableResourceServer
@Profile(Constant.RESOURCE_SERVER_PROFILE)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	@Value("${security.oauth2.resource.id}")
    private String resourceId;

    // The DefaultTokenServices bean provided at the AuthorizationConfig
    @Autowired
    private DefaultTokenServices tokenServices;

    // The TokenStore bean provided at the AuthorizationConfig
    @Autowired
    private TokenStore tokenStore;

    // To allow the rResourceServerConfigurerAdapter to understand the token,
    // it must share the same characteristics with AuthorizationServerConfigurerAdapter.
    // So, we must wire it up the beans in the ResourceServerSecurityConfigurer.
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(resourceId)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new OAuthRequestedMatcher())
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // when restricting access to 'Roles' you must remove the "ROLE_" part role
                // for "ROLE_USER" use only "USER"
                .antMatchers("/api/hello").access("hasAnyRole('USER')")          
                .antMatchers("/api/admin").hasRole("ADMIN")
                // restricting all access to /api/** to authenticated users
                .antMatchers("/api/**").authenticated();
    }

    private static class OAuthRequestedMatcher implements RequestMatcher {
        public boolean matches(HttpServletRequest request) {
            // Determine if the resource called is "/api/**"
            String path = request.getServletPath();
            if ( path.length() >= 5 ) {
              path = path.substring(0, 5);
              boolean isApi = path.equals("/api/");
              return isApi;
            } else return false;
        }
    }
    
//	private static final String DEMO_RESOURCE_ID = "order";
//
//	 
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//                // Since we want the protected resources to be accessible in the UI as well we need
//                // session creation to be allowed (it's disabled by default in 2.0.6)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .requestMatchers().anyRequest()
//                .and()
//                .anonymous()
//                .and()
//                .authorizeRequests()
////                .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
//                .antMatchers("/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问
//        // @formatter:on
//    }
//}
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//	private static final String DEMO_RESOURCE_ID = "order";
//	
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) {
//		resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		// @formatter:off
//		http
//				// Since we want the protected resources to be accessible in the UI as well we
//				// need
//				// session creation to be allowed (it's disabled by default in 2.0.6)
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().requestMatchers()
//				.anyRequest().and().anonymous().and().authorizeRequests()
//				// .antMatchers("/product/**").access("#oauth2.hasScope('select') and
//				// hasRole('ROLE_USER')")
//				.antMatchers("/order/**").authenticated();// 配置order访问控制，必须认证过后才可以访问
//		// @formatter:on
//	}
}
