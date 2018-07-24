package idv.hsiehpinghan.springcloudstarterhystrixboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyServiceTest {
	@Autowired
	private MyService myService;

	@Test
	public void longRun() throws Exception {
		longRun_normal();
		longRun_timeout();
	}

	private void longRun_normal() throws InterruptedException {
		Long milliseconds = 0L;
		Long result = myService.longRun(milliseconds);
		Assert.assertEquals(milliseconds.longValue(), result.longValue());
	}

	private void longRun_timeout() throws InterruptedException {
		Long milliseconds = 1000L;
		Long result = myService.longRun(milliseconds);
		Assert.assertEquals(Long.MAX_VALUE, result.longValue());
	}
}
