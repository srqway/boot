package idv.hsiehpinghan.springbootstarterloggingboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class LoggerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTest.class);
	private static final int LOOP_AMOUNT = 1000;

	@Test
	public void basicTest() throws Exception {
		for (int i = 0; i < LOOP_AMOUNT; ++i) {
			LOGGER.trace("trace");
			LOGGER.debug("debug");
			LOGGER.info("info");
			LOGGER.warn("warn");
			LOGGER.error("error");
		}
	}

}
