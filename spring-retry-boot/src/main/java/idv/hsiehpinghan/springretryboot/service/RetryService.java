package idv.hsiehpinghan.springretryboot.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryService {
	private final AtomicInteger successAtomicInteger = new AtomicInteger(0);
	private final AtomicInteger failAtomicInteger = new AtomicInteger(0);

	@Retryable(include = { Exception.class }, maxAttempts = 3, backoff = @Backoff(value = 1000))
	public String doSuccess() {
		int retryAmount = successAtomicInteger.incrementAndGet();
		if (retryAmount <= 2) {
			System.err.println(String.format("doSuccess retryAmount(%d)", retryAmount));
			throw new RuntimeException("doSuccess fail !!!");
		}
		return "success";
	}

	@Retryable(include = { Exception.class }, maxAttempts = 3, backoff = @Backoff(value = 1000))
	public String doFail() {
		int retryAmount = failAtomicInteger.incrementAndGet();
		if (retryAmount <= 3) {
			System.err.println(String.format("doFail retryAmount(%d)", retryAmount));
			throw new RuntimeException("doFail fail !!!");
		}
		return "success";
	}

	@Recover
	public String recover(Exception exception) {
		return "recover doFail fail !!!";
	}
}
