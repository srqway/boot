package idv.hsiehpinghan.springbootstartermail.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {
	private String from = "thank.hsiehpinghan@gmail.com";
	private String to = "thank.hsiehpinghan@gmail.com";
	private String subject = "subject";
	private String msg = "message";
	@Autowired
	private MailService mailService;

	@Test
	public void sendMail() throws MessagingException {
		mailService.sendMail(from, to, subject, msg, false);
	}

	@Test
	public void sendMailWithAttachment() throws MessagingException, IOException {
		Map<String, File> map = new HashMap<String, File>(2);
		File jpeg = new ClassPathResource("file/jpeg.jpeg").getFile();
		map.put("jpeg", jpeg);
		File xls = new ClassPathResource("file/xls.xls").getFile();
		map.put("xls", xls);
		mailService.sendMailWithAttachment(from, to, subject, msg, false, map);
	}

	@Test
	public void sendMailWithInline() throws MessagingException, IOException {
		Map<String, File> map = new HashMap<String, File>(1);
		StringBuilder sb = new StringBuilder();
		sb.append("<html> ");
		sb.append("<body> ");
		sb.append("<img src='cid:jpeg'> ");
		sb.append("</body> ");
		sb.append("</html> ");
		File jpeg = new ClassPathResource("file/jpeg.jpeg").getFile();
		map.put("jpeg", jpeg);
		mailService.sendMailWithInline(from, to, subject, sb.toString(), true, map);
	}
}
