package idv.hsiehpinghan.springkafkaboot.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import idv.hsiehpinghan.springkafkaboot.constant.KafkaConstant;
import idv.hsiehpinghan.springkafkaboot.model.JsonModel;

@Component
@Profile("json")
public class JsonProducer {
	private final Logger LOGGER = LoggerFactory.getLogger(JsonProducer.class);
	@Autowired
	@Qualifier("jsonKafkaTemplate")
	private KafkaTemplate<Integer, JsonModel> kafkaTemplate;

	public ListenableFuture<SendResult<Integer, JsonModel>> send(Integer key, JsonModel payload) {
		LOGGER.info("send key({}) payload({})", key, payload);
		ListenableFuture<SendResult<Integer, JsonModel>> listenableFuture = kafkaTemplate.send(KafkaConstant.JSON_TOPIC,
				key, payload);
		return listenableFuture;
	}
}
