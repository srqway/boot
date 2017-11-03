package idv.hsiehpinghan.springkafkaboot.consumer;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springkafkaboot.constant.KafkaConstant;

@Component
public class BatchConsumer {
	private final Logger LOGGER = LoggerFactory.getLogger(BatchConsumer.class);
	private CountDownLatch countDownLatch;

	@KafkaListener(topics = KafkaConstant.BATCH_TOPIC, containerFactory = "batchKafkaListenerContainerFactory")
	public void receive(List<ConsumerRecord<Integer, String>> consumerRecords) {
		LOGGER.info("receive size({})", consumerRecords.size());
		for (ConsumerRecord<Integer, String> consumerRecord : consumerRecords) {
			LOGGER.info("receive consumerRecord({})", consumerRecord);
			countDownLatch.countDown();
		}
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

}
