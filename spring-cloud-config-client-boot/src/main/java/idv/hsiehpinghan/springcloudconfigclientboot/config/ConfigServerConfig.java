package idv.hsiehpinghan.springcloudconfigclientboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigServerConfig {
	@Value("${configuration.property}")
	private String configurationProperty;

	public String getConfigurationProperty() {
		return configurationProperty;
	}

}
