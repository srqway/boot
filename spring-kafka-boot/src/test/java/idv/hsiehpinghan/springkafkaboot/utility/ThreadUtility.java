package idv.hsiehpinghan.springkafkaboot.utility;

public class ThreadUtility {
	private static final long FIVE_SECONDS = 1000 * 5;

	public static void waitKafkaListenerStart() throws InterruptedException {
		Thread.sleep(FIVE_SECONDS);
	}

	public static void waitKafkaListenerStop() throws InterruptedException {
		Thread.sleep(FIVE_SECONDS);
	}
}
