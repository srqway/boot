package idv.hsiehpinghan.springbootstartercacheboot.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import idv.hsiehpinghan.springbootstartercacheboot.SpringBootStarterCacheBootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBootStarterCacheBootApplication.class })
public class ObjectRepositoryTest {
	@Autowired
	private ObjectRepository objectRepository;

	@Test
	public void getString() {
		Assert.assertEquals(0, objectRepository.getNewStringCount());
		objectRepository.getString("aaa");
		Assert.assertEquals(1, objectRepository.getNewStringCount());
		objectRepository.getString("aaa");
		Assert.assertEquals(1, objectRepository.getNewStringCount());
	}
}
