package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterwebboot2.service.CrudService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CrudControllerTest {
	private static final Integer ID = 3;
	private static final String STRING = "string_3";
	private static final int TOTAL_COUNT = 10;
	private static final List<CrudEntity> TOTAL_ENTITIES = generateTotalEntities();
	@LocalServerPort
	private int port;
	@Autowired
	private CrudService crudService;
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
		delete_no_content();
	}

	@Test
	public void test04_readAll() throws Exception {
		readAll_no_content();
		for (CrudEntity entity : TOTAL_ENTITIES) {
			crudService.save(entity);
		}
		readAll_partial_content();
		readAll_ok();
	}

	private void readAll_no_content() throws IOException, Exception {
		int page = 0;
		int size = 3;
		List<Direction> directions = Arrays.asList(Direction.ASC, Direction.DESC);
		List<String> properties = Arrays.asList("id", "string");
		String directionsStr = directions.stream().map(x -> "directions=" + x).collect(Collectors.joining("&"));
		String propertiesStr = properties.stream().map(x -> "properties=" + x).collect(Collectors.joining("&"));
		String url = String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr);
		HttpEntity<CrudUpdateCriteria> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
	}

	private void readAll_partial_content() throws IOException, Exception {
		int page = 1;
		int size = 3;
		List<Direction> directions = Arrays.asList(Direction.ASC, Direction.DESC);
		List<String> properties = Arrays.asList("id", "string");
		String directionsStr = directions.stream().map(x -> "directions=" + x).collect(Collectors.joining("&"));
		String propertiesStr = properties.stream().map(x -> "properties=" + x).collect(Collectors.joining("&"));
		String url = String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr);
		HttpEntity<CrudUpdateCriteria> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<List<CrudEntity>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				List.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.PARTIAL_CONTENT);
		Assertions.assertThat(responseEntity.getHeaders().get("X-Total-Count")).contains(String.valueOf(TOTAL_COUNT));
		Assertions.assertThat(responseEntity.getHeaders().get("first")).contains("/api/cruds?page=0&size=3");
		Assertions.assertThat(responseEntity.getHeaders().get("last")).contains("/api/cruds?page=3&size=3");
		Assertions.assertThat(responseEntity.getHeaders().get("next")).contains("/api/cruds?page=2&size=3");
		Assertions.assertThat(responseEntity.getHeaders().get("prev")).contains("/api/cruds?page=0&size=3");
		Assertions.assertThat(responseEntity.getBody()).size().isEqualTo(3);
	}

	private void readAll_ok() throws IOException, Exception {
		int page = 0;
		int size = 10;
		List<Direction> directions = Arrays.asList(Direction.ASC, Direction.DESC);
		List<String> properties = Arrays.asList("id", "string");
		String directionsStr = directions.stream().map(x -> "directions=" + x).collect(Collectors.joining("&"));
		String propertiesStr = properties.stream().map(x -> "properties=" + x).collect(Collectors.joining("&"));
		String url = String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr);
		HttpEntity<CrudUpdateCriteria> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<List<CrudEntity>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				List.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getHeaders().get("X-Total-Count")).contains(String.valueOf(TOTAL_COUNT));
		Assertions.assertThat(responseEntity.getBody()).size().isEqualTo(10);
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

	private void delete_no_content() throws IOException, Exception {
		String url = String.format("http://localhost:%d/api/cruds/%d", port, ID);
		HttpEntity<?> requestEntity = null;
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity,
				Object.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
	}

	private void update_not_found() throws IOException, Exception {
		final Integer NOT_EXIST_ID = Integer.MAX_VALUE;
		final String NEW_STRING = "new_string";
		String url = String.format("http://localhost:%d/api/cruds/%d", port, NOT_EXIST_ID);
		CrudUpdateCriteria criteria = new CrudUpdateCriteria(NEW_STRING);
		HttpEntity<CrudUpdateCriteria> requestEntity = new HttpEntity<CrudUpdateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity,
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

	private static List<CrudEntity> generateTotalEntities() {
		List<CrudEntity> entities = new ArrayList<>(TOTAL_COUNT);
		for (int i = 0; i < TOTAL_COUNT; ++i) {
			Integer id = i;
			String string = "string_" + i;
			CrudEntity entity = new CrudEntity(id, string);
			entities.add(entity);
		}
		return entities;
	}
}
