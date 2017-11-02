package idv.hsiehpinghan.springkafkaboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringKafkaBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaBootApplication.class, args);
	}
}
