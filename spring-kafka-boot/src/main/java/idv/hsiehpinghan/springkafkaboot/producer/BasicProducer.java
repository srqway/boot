package idv.hsiehpinghan.springkafkaboot.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import idv.hsiehpinghan.springkafkaboot.constant.KafkaConstant;

@Component
public class BasicProducer {
	private final Logger LOGGER = LoggerFactory.getLogger(BasicProducer.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public ListenableFuture<SendResult<String, String>> send(String payload) {
		LOGGER.info("send payload({})", payload);
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(KafkaConstant.BASIC_TOPIC,
				payload);
		kafkaTemplate.flush();
		return listenableFuture;
	}
}
