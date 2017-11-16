package idv.hsiehpinghan.springcloudstartereurekaboot.client;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscoveryClientTest {
	private final Integer ID = 0;
	private RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private Environment env;
	@Autowired
	private DiscoveryClient discoveryClient;

	@Test
	public void test() {
		String applicationName = env.getRequiredProperty("spring.application.name");
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(applicationName);
		String serviceUri = String.format("%s/v1/basic/read/%d", serviceInstances.get(0).getUri().toString(), ID);
		ResponseEntity<String> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, String.class);
		Assert.assertEquals(String.format("String_%d", ID), restExchange.getBody());
		System.err.println();
	}
}
