package idv.hsiehpinghan.springbootstarterdatajpaboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class SpringBootStarterDataJpaBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterDataJpaBootApplication.class, args);
	}
}
