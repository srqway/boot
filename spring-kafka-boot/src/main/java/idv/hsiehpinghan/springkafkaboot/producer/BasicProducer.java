package idv.hsiehpinghan.springkafkaboot.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import idv.hsiehpinghan.springkafkaboot.constant.KafkaConstant;

@Component
@Profile("basic")
public class BasicProducer {
	private final Logger LOGGER = LoggerFactory.getLogger(BasicProducer.class);
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	public ListenableFuture<SendResult<Integer, String>> send(Integer key, String payload) {
		LOGGER.info("send key({}) payload({})", key, payload);
		ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(KafkaConstant.BASIC_TOPIC,
				key, payload);
		return listenableFuture;
	}
}
