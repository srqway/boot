package idv.hsiehpinghan.springbootstarterwebboot2.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineSpringConfiguration {
	@Bean
	public CommandLineRunner commaind() {
		return args -> {
			System.err.print("my command line runner");
		};
	}
}
