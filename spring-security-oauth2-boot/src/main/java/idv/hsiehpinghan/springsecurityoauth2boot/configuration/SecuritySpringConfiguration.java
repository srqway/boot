package idv.hsiehpinghan.springsecurityoauth2boot.configuration;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity( debug = true )
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecuritySpringConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
                .formLogin().disable() // disable form authentication
                .anonymous().disable() // disable anonymous user
                .httpBasic().and()
                .authorizeRequests().anyRequest().denyAll(); // denying all access
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // creating user in memory
                .withUser("user")
                    .password("password").roles("USER")
                .and().withUser("admin")
                    .password("password").authorities("ROLE_ADMIN");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }
}

//@Configuration
//@EnableWebSecurity
//public class SecuritySpringConfiguration extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private UserDetailsService userDetailsService;
//	@Autowired
//	private UserService userService;
//
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		// for restful service
//		httpSecurity.csrf().disable();
//
//		httpSecurity.authorizeRequests().antMatchers("/oauth/*").permitAll();
//	}
//
//	@PostConstruct
//	public void postConstruct() {
//		if (userService.findOne("user") == null) {
//			UserEntity user = new UserEntity("user", "user", true, true, true, true,
//					Arrays.asList(new RoleEntity("ROLE_USER", "user role name", null)));
//			userService.save(user);
//		}
//		if (userService.findOne("admin") == null) {
//			UserEntity admin = new UserEntity("admin", "admin", true, true, true, true,
//					Arrays.asList(new RoleEntity("ROLE_ADMIN", "admin role name", null)));
//			userService.save(admin);
//		}
//	}
//
//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(userDetailsService);
//	}
//}