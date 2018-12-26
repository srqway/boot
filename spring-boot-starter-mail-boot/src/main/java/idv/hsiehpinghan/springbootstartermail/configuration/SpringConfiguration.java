package idv.hsiehpinghan.springbootstartermail.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SpringConfiguration {
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private Integer port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.smtp.auth}")
	private String auth;
	@Value("${spring.mail.smtp.starttls.enable}")
	private String starttls;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setJavaMailProperties(getProperties());
		return javaMailSender;
	}

	private Properties getProperties() {
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", auth);
		prop.setProperty("mail.smtp.starttls.enable", starttls);
		return prop;
	}
}