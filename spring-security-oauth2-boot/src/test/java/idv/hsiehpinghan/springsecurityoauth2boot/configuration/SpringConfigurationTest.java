package idv.hsiehpinghan.springsecurityoauth2boot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfigurationTest {

	@Bean
	public RestTemplate restTemplate() throws Exception {
		return new RestTemplate();
	}

}
