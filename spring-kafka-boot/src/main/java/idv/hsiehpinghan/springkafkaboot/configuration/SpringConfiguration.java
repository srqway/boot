package idv.hsiehpinghan.springkafkaboot.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class SpringConfiguration {
//	private String bootstrapServers;
//	private String groupId;
//	@Autowired
//	private Environment environment;
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		bootstrapServers = environment.getRequiredProperty("bootstrap_servers");
//		groupId = environment.getRequiredProperty("group_id");
//	}
//
//	@Bean
//	public KafkaTemplate<Integer, String> basicKafkaTemplate() {
//		ProducerFactory<Integer, String> producerFactory = generateBasicProducerFactory();
//		return new KafkaTemplate<>(producerFactory);
//	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<Integer, String> basicKafkaListenerContainerFactory() {
//		ConcurrentKafkaListenerContainerFactory<Integer, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
//		concurrentKafkaListenerContainerFactory.setConsumerFactory(generateBasicConsumerFactory());
//		return concurrentKafkaListenerContainerFactory;
//	}
//
//	private ConsumerFactory<Integer, String> generateBasicConsumerFactory() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		return new DefaultKafkaConsumerFactory<>(props);
//	}
//
//	private ProducerFactory<Integer, String> generateBasicProducerFactory() {
//		Map<String, Object> configMap = new HashMap<>();
//		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
//		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		return new DefaultKafkaProducerFactory<>(configMap);
//	}

}