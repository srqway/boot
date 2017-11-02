package idv.hsiehpinghan.springbootstartercacheboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import idv.hsiehpinghan.springbootstartercacheboot.SpringBootStarterCacheBootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBootStarterCacheBootApplication.class })
public class CacheManagerServiceTest {
	private final String KEY = "key";
	private final String VALUE = "value";

	@Autowired
	private CacheManagerService cacheManagerService;

	@Test
	public void putAndGet() {
		cacheManagerService.put(KEY, VALUE);
		String value = cacheManagerService.get(KEY);
		Assert.assertEquals(VALUE, value);
	}
}
