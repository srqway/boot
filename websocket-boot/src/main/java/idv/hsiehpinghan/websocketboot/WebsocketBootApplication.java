package idv.hsiehpinghan.websocketboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebsocketBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketBootApplication.class, args);
	}
}
