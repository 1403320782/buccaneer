package log4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Log4jTest {
	@Test
	public void test(){
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.error("error");
		logger.info("info");
		logger.warn("warn");
	}
}
