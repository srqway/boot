package idv.hsiehpinghan.springkafkaboot.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import idv.hsiehpinghan.springkafkaboot.consumer.JsonConsumer;
import idv.hsiehpinghan.springkafkaboot.model.JsonModel;
import idv.hsiehpinghan.springkafkaboot.producer.JsonProducer;

@SpringBootTest
@ActiveProfiles("json")
@RunWith(SpringRunner.class)
public class JsonTest {
	private final int SIZE = 3;
	private final long FIVE_SECONDS = 1000 * 5;
	@Autowired
	private JsonProducer jsonProducer;
	@Autowired
	private JsonConsumer jsonConsumer;

	@Test
	public void sendAndReceive() throws Exception {
		jsonConsumer.setCountDownLatch(new CountDownLatch(SIZE));
		for (int i = 0; i < SIZE; ++i) {
			JsonModel jsonModel = new JsonModel("JsonTest");
			jsonProducer.send(i, jsonModel);
		}
		CountDownLatch countDownLatch = jsonConsumer.getCountDownLatch();
		countDownLatch.await(10, TimeUnit.SECONDS);
		assertThat(countDownLatch.getCount()).isEqualTo(0);
		waitKafkaListenerStop();
	}

	private void waitKafkaListenerStop() throws InterruptedException {
		Thread.sleep(FIVE_SECONDS);
	}
}
