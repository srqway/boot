package idv.hsiehpinghan.springretryboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RetryServiceTest {
	@Autowired
	private RetryService retryService;

	@Test
	public void doSuccess() {
		Assert.assertEquals("success", retryService.doSuccess());
	}

	@Test
	public void doFail() {
		Assert.assertEquals("recover doFail fail !!!", retryService.doFail());
	}
}
