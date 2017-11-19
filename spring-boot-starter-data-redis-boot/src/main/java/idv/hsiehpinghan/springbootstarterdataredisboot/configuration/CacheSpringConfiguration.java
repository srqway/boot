package idv.hsiehpinghan.springbootstarterdataredisboot.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
@Profile("cache")
public class CacheSpringConfiguration {

}