package idv.hsiehpinghan.springcloudstartereurekaboot.configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "service_0", "service_1" })
@EnableDiscoveryClient
public class ServiceSpringConfiguration {

}
