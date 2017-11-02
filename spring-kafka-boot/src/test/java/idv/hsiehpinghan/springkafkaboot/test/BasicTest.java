package idv.hsiehpinghan.springkafkaboot.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import idv.hsiehpinghan.springkafkaboot.constant.KafkaConstant;
import idv.hsiehpinghan.springkafkaboot.consumer.BasicConsumer;
import idv.hsiehpinghan.springkafkaboot.producer.BasicProducer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicTest {
	@ClassRule
	public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, KafkaConstant.BASIC_TOPIC);
	@Autowired
	private BasicProducer basicProducer;
	@Autowired
	private BasicConsumer basicConsumer;

	@Test
	public void sendAndReceive() throws Exception {
		// TODO : not success (https://www.codenotfound.com/spring-kafka-consumer-producer-example.html)
		basicConsumer.setCountDownLatch(new CountDownLatch(1));
		basicProducer.send("BasicTest");
		CountDownLatch countDownLatch = basicConsumer.getCountDownLatch();
		countDownLatch.await(10, TimeUnit.SECONDS);
		assertThat(countDownLatch.getCount()).isEqualTo(0);
	}
}
