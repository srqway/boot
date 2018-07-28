package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.io.IOException;
import java.util.Collections;

import org.assertj.core.api.Assertions;
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
import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CrudControllerTest {
	private static final Integer ID = 3;
	private static final String STRING = "string_3";
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test00_create() throws Exception {
		create_created();
		create_conflict();
	}

	@Test
	public void test01_read() throws Exception {
		read_not_found();
		read_ok();
	}

	@Test
	public void test02_update() throws Exception {
		update_not_found();
		update_ok();
	}

	@Test
	public void test03_delete() throws Exception {
		delete_not_found();
		delete_ok();
	}

	private void delete_not_found() throws IOException, Exception {
		final Integer NOT_EXIST_ID = Integer.MAX_VALUE;
		String url = String.format("http://localhost:%d/api/cruds/%d", port, NOT_EXIST_ID);
		HttpEntity<?> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity,
				Object.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
	}

	private void delete_ok() throws IOException, Exception {
		String url = String.format("http://localhost:%d/api/cruds/%d", port, ID);
		HttpEntity<?> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity,
				Object.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	}

	private void update_not_found() throws IOException, Exception {
		final Integer NOT_EXIST_ID = Integer.MAX_VALUE;
		final String NEW_STRING = "new_string";
		String url = String.format("http://localhost:%d/api/cruds/%d", port, NOT_EXIST_ID);
		CrudUpdateCriteria criteria = new CrudUpdateCriteria(NEW_STRING);
		HttpEntity<CrudUpdateCriteria> requestEntity = new HttpEntity<CrudUpdateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
	}

	private void update_ok() throws IOException, Exception {
		final String NEW_STRING = "new_string";
		String url = String.format("http://localhost:%d/api/cruds/%d", port, ID);
		CrudUpdateCriteria criteria = new CrudUpdateCriteria(NEW_STRING);
		HttpEntity<CrudUpdateCriteria> requestEntity = new HttpEntity<CrudUpdateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getHeaders().get("Location"))
				.contains(String.format("http://localhost:%d/api/cruds/%d", port, ID));
		Assertions.assertThat(responseEntity.getBody())
				.isEqualToComparingFieldByFieldRecursively(new CrudEntity(ID, NEW_STRING));
	}

	private void read_not_found() throws IOException, Exception {
		final Integer NOT_EXIST_ID = Integer.MAX_VALUE;
		String url = String.format("http://localhost:%d/api/cruds/%d", port, NOT_EXIST_ID);
		HttpEntity<?> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
		Assertions.assertThat(responseEntity.getBody()).isNull();
	}

	private void read_ok() throws IOException, Exception {
		String url = String.format("http://localhost:%d/api/cruds/%d", port, ID);
		HttpEntity<Object> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody())
				.isEqualToComparingFieldByFieldRecursively(new CrudEntity(ID, STRING));
	}

	private void create_created() {
		String url = String.format("http://localhost:%d/api/cruds", port);
		CrudCreateCriteria criteria = new CrudCreateCriteria(ID, STRING);
		HttpEntity<CrudCreateCriteria> requestEntity = new HttpEntity<CrudCreateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
		Assertions.assertThat(responseEntity.getHeaders().get("Location"))
				.contains(String.format("http://localhost:%d/api/cruds/%d", port, ID));
		Assertions.assertThat(responseEntity.getBody())
				.isEqualToComparingFieldByFieldRecursively(new CrudEntity(ID, STRING));
	}

	private void create_conflict() {
		String url = String.format("http://localhost:%d/api/cruds", port);
		CrudCreateCriteria criteria = new CrudCreateCriteria(ID, STRING);
		HttpEntity<CrudCreateCriteria> requestEntity = new HttpEntity<CrudCreateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class,
				Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CONFLICT);
		Assertions.assertThat(responseEntity.getBody()).isNull();
	}

}
