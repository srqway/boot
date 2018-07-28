package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CrudControllerTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void create() throws Exception {
		create_created();
		create_conflict();
	}

	private void create_created() {
		String url = String.format("http://localhost:%d/api/cruds", port);
		Integer id = 3;
		String string = "string_3";
		CrudCreateCriteria criteria = new CrudCreateCriteria(id, string);
		HttpEntity<CrudCreateCriteria> requestEntity = new HttpEntity<CrudCreateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class,
				Collections.EMPTY_MAP);
		assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
		assertThat(responseEntity.getHeaders().get("Location"))
				.contains(String.format("http://localhost:%d/api/cruds/%d", port, id));
		assertThat(responseEntity.getBody()).isNull();
	}

	private void create_conflict() {
		String url = String.format("http://localhost:%d/api/cruds", port);
		Integer id = 3;
		String string = "string_3";
		CrudCreateCriteria criteria = new CrudCreateCriteria(id, string);
		HttpEntity<CrudCreateCriteria> requestEntity = new HttpEntity<CrudCreateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class,
				Collections.EMPTY_MAP);
		assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CONFLICT);
		assertThat(responseEntity.getBody()).isNull();
	}

}
