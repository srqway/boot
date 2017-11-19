package idv.hsiehpinghan.springbootstarterdataredisboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("cache")
public class CachedService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CachedService.class);
	private int count = 0;

	@Cacheable(value = "stringCache")
	public String getString(String string) {
		++count;
		LOGGER.info(String.format("getString(%s), count(%d)", string, count));
		return string;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
