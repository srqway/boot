package idv.hsiehpinghan.springkafkaboot.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class SpringConfiguration implements InitializingBean {
	private String bootstrapServers;
	private String groupId;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		bootstrapServers = environment.getRequiredProperty("spring.kafka.bootstrap-servers");
		groupId = environment.getRequiredProperty("spring.kafka.consumer.group-id");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> batchKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(generateBatchConsumerFactory());
		concurrentKafkaListenerContainerFactory.setBatchListener(true);
		return concurrentKafkaListenerContainerFactory;
	}

	private ConsumerFactory<Integer, String> generateBatchConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);
		return new DefaultKafkaConsumerFactory<>(props);
	}

}