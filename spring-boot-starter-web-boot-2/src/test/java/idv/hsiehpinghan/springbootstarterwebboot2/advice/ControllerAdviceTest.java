package idv.hsiehpinghan.springbootstarterwebboot2.advice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerAdviceTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void runtimeException() {
		final Integer ID = null;
		final String STRING = "string_3";
		String url = String.format("http://localhost:%d/api/cruds", port);
		CrudCreateCriteria criteria = new CrudCreateCriteria(ID, STRING);
		HttpEntity<CrudCreateCriteria> requestEntity = new HttpEntity<CrudCreateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
