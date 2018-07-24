package idv.hsiehpinghan.springcloudstartereurekaboot.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ActiveProfiles("client")
@RunWith(SpringRunner.class)
public class DiscoveryClientTest {
	private final String SERVICE = "spring-cloud-starter-eureka-boot";
	private final Integer ID = 0;
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test() {
		String serviceUri = String.format("http://%s/v1/basic/read/%d", SERVICE, ID);
		ResponseEntity<String> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, String.class);
		Assert.assertEquals(String.format("String_%d", ID), restExchange.getBody());
	}
}
