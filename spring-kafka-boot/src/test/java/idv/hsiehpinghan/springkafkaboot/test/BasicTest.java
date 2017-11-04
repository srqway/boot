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

import idv.hsiehpinghan.springkafkaboot.consumer.BasicConsumer;
import idv.hsiehpinghan.springkafkaboot.producer.BasicProducer;
import idv.hsiehpinghan.springkafkaboot.utility.ThreadUtility;

@SpringBootTest
@ActiveProfiles("basic")
@RunWith(SpringRunner.class)
public class BasicTest {
	private final int SIZE = 3;
	@Autowired
	private BasicProducer basicProducer;
	@Autowired
	private BasicConsumer basicConsumer;

	@Test
	public void sendAndReceive() throws Exception {
		ThreadUtility.waitKafkaListenerStart();
		basicConsumer.setCountDownLatch(new CountDownLatch(SIZE));
		for (int i = 0; i < SIZE; ++i) {
			basicProducer.send(i, "BasicTest");
		}
		CountDownLatch countDownLatch = basicConsumer.getCountDownLatch();
		countDownLatch.await(10, TimeUnit.SECONDS);
		assertThat(countDownLatch.getCount()).isEqualTo(0);
		ThreadUtility.waitKafkaListenerStop();
	}

}
