package idv.hsiehpinghan.springkafkaboot.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaboot.constant.KafkaConstant;

@Component
@Profile("basic")
public class BasicConsumer {
	private final Logger LOGGER = LoggerFactory.getLogger(BasicConsumer.class);
	private CountDownLatch countDownLatch;

	@KafkaListener(topics = KafkaConstant.BASIC_TOPIC)
	public void receive(ConsumerRecord<Integer, String> consumerRecord) {
		LOGGER.info("receive consumerRecord({})", consumerRecord);
		countDownLatch.countDown();
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

}
