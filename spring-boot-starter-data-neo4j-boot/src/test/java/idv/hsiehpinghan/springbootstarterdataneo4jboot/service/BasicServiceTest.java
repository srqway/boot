package idv.hsiehpinghan.springbootstarterdataneo4jboot.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import idv.hsiehpinghan.springbootstarterdataneo4jboot.node.BasicNode;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicServiceTest {
	@Autowired
	private BasicService service;

	@Test
	public void test00_init() {
		service.deleteAll();
	}
	
	@Test
	public void test01_save() {
		BasicNode basicNode = new BasicNode();
		basicNode.setId("vvv");
		basicNode.setIntegerString(999);
		service.save(basicNode);
	}
	
//	@Test
//	public void findById() {
//		throw new RuntimeException("Test not implemented");
//	}
//
//	@Test
//	public void query() {
//		throw new RuntimeException("Test not implemented");
//	}


}
