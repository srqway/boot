package idv.hsiehpinghan.springcloudstarterhystrixboot.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
//@formatter:off
@DefaultProperties(
	commandProperties = {
		@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
		@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
		@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
		@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
		@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
	}
)
//@formatter:on
public class MyService {

	// @formatter:off
	@HystrixCommand(
		fallbackMethod = "fallbackMethod", 
		threadPoolKey = "threadPoolKey_0", 
		threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "10"), 
			@HystrixProperty(name = "maxQueueSize", value = "100") 
		},
		commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
			@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
		}
	)
	// @formatter:on
	public long longRun(long milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
		return milliseconds;
	}

	public long fallbackMethod(long milliseconds) {
		return Long.MAX_VALUE;
	}
}
