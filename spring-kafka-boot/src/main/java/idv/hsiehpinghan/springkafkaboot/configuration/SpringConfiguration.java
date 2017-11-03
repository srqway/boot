package idv.hsiehpinghan.springkafkaboot.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import idv.hsiehpinghan.springkafkaboot.model.JsonModel;

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
	@Profile("json")
	public KafkaTemplate<Integer, JsonModel> jsonKafkaTemplate() {
		ProducerFactory<Integer, JsonModel> producerFactory = generateJsonProducerFactory();
		return new KafkaTemplate<>(producerFactory);
	}

	@Bean
	@Profile("batch")
	public ConcurrentKafkaListenerContainerFactory<Integer, String> batchKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(generateBatchConsumerFactory());
		concurrentKafkaListenerContainerFactory.setBatchListener(true);
		return concurrentKafkaListenerContainerFactory;
	}

	@Bean
	@Profile("json")
	public ConcurrentKafkaListenerContainerFactory<String, JsonModel> jsonKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, JsonModel> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(generateJsonConsumerFactory());
		return concurrentKafkaListenerContainerFactory;
	}

	private ProducerFactory<Integer, JsonModel> generateJsonProducerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		Serializer<Integer> keySerializer = new IntegerSerializer();
		Serializer<JsonModel> valueSerializer = new JsonSerializer<JsonModel>();
		return new DefaultKafkaProducerFactory<>(configMap, keySerializer, valueSerializer);
	}

	private ConsumerFactory<String, JsonModel> generateJsonConsumerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		Deserializer<String> keyDeserializer = new StringDeserializer();
		Deserializer<JsonModel> valueDeserializer = new JsonDeserializer<>(JsonModel.class);
		return new DefaultKafkaConsumerFactory<>(configMap, keyDeserializer, valueDeserializer);
	}

	private ConsumerFactory<Integer, String> generateBatchConsumerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);
		return new DefaultKafkaConsumerFactory<>(configMap);
	}

}