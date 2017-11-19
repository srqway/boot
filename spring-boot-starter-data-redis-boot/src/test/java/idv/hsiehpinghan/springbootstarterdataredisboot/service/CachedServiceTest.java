package idv.hsiehpinghan.springbootstarterdataredisboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("cache")
@RunWith(SpringRunner.class)
public class CachedServiceTest {
	@Autowired
	private CachedService cachedService;

	@Test
	public void getString() {
		long now = System.currentTimeMillis();
		String string_0 = String.format("string_0_%d", now);
		String string_1 = String.format("string_1_%d", now);
		cachedService.getString(string_0);
		Assert.assertEquals(1, cachedService.getCount());
		cachedService.getString(string_1);
		Assert.assertEquals(2, cachedService.getCount());
		cachedService.getString(string_0);
		Assert.assertEquals(2, cachedService.getCount());
		cachedService.getString(string_1);
		Assert.assertEquals(2, cachedService.getCount());

	}
}
