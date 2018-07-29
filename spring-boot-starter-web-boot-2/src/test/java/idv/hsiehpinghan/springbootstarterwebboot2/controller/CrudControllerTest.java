package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

//	@Test
//	public void test00_create() throws Exception {
//		create_created();
//		create_conflict();
//	}
//
//	@Test
//	public void test01_read() throws Exception {
//		read_not_found();
//		read_ok();
//	}
//
//	@Test
//	public void test02_update() throws Exception {
//		update_not_found();
//		delete_no_content();
//	}
//
//	@Test
//	public void test03_delete() throws Exception {
//		delete_not_found();
//		delete_ok();
//	}
	
	@Test
	public void test04_readAll() throws Exception {
		readAll_no_content();
		for(CrudEntity entity : TOTAL_ENTITIES) {
			crudService.save(entity);
		}
		readAll_partial_content();
//		readAll_ok();
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
		for(CrudEntity crudEntity : responseEntity.getBody()) {
			Integer id = crudEntity.getId();
			String string = "string_" + id;
			Assertions.assertThat(crudEntity).isEqualToComparingFieldByFieldRecursively(new CrudEntity(id, string));
		}
//		Assertions.assertThat(responseEntity.getBody()).usingFieldByFieldElementComparator().contains(TOTAL_ENTITIES.subList(3, 6).toArray(new CrudEntity[]{}));
		
		
//		int page = 1;
//		int size = 3;
//		List<Direction> directions = Arrays.asList(Direction.ASC, Direction.DESC);
//		List<String> properties = Arrays.asList("id", "string");
//		String directionsStr = directions.stream().map(x -> "directions=" + x).collect(Collectors.joining("&"));
//		String propertiesStr = properties.stream().map(x -> "properties=" + x).collect(Collectors.joining("&"));
//		List<Order> orders = new LinkedList<>();
//		for (int i = 0, sz = properties.size(); i < sz; ++i) {
//			Direction direction = directions.get(i);
//			String property = properties.get(i);
//			Order order = new Order(direction, property, NullHandling.NULLS_LAST);
//			orders.add(order);
//		}
//		Sort sort = Sort.by(orders);
//		Pageable pageable = PageRequest.of(page, size, sort);
//		List<CrudEntity> content = Arrays.asList(new CrudEntity(3, "string_3"), new CrudEntity(4, "string_4"),
//				new CrudEntity(5, "string_5"));
//		long total = 10L;
//		Page<CrudEntity> entities = new PageImpl<>(content, pageable, total);
//		Mockito.when(crudService.findAll(pageable)).thenReturn(entities);
//		// @formatter:off
//		mockMvc.perform(
//			MockMvcRequestBuilders.get(String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr))
//			.contentType(MediaType.APPLICATION_JSON)
//		)
//		.andExpect(MockMvcResultMatchers.status().isPartialContent())
//		.andExpect(MockMvcResultMatchers.header().string("X-Total-Count", Matchers.is(String.valueOf(total))))
//		.andExpect(MockMvcResultMatchers.header().string("", Matchers.is()))
//		.andExpect(MockMvcResultMatchers.header().string("", Matchers.is()))
//		.andExpect(MockMvcResultMatchers.header().string("", Matchers.is()))
//		.andExpect(MockMvcResultMatchers.header().string("", Matchers.is()))
//        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
//        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.contains(3, 4, 5)))
//        .andExpect(MockMvcResultMatchers.jsonPath("$[*].string", Matchers.contains("string_3", "string_4", "string_5")))
//		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}
//
//	private void readAll_ok() throws IOException, Exception {
//		int page = 0;
//		int size = 10;
//		List<Direction> directions = Arrays.asList(Direction.ASC, Direction.DESC);
//		List<String> properties = Arrays.asList("id", "string");
//		String directionsStr = directions.stream().map(x -> "directions=" + x).collect(Collectors.joining("&"));
//		String propertiesStr = properties.stream().map(x -> "properties=" + x).collect(Collectors.joining("&"));
//		List<Order> orders = new LinkedList<>();
//		for (int i = 0, sz = properties.size(); i < sz; ++i) {
//			Direction direction = directions.get(i);
//			String property = properties.get(i);
//			Order order = new Order(direction, property, NullHandling.NULLS_LAST);
//			orders.add(order);
//		}
//		Sort sort = Sort.by(orders);
//		Pageable pageable = PageRequest.of(page, size, sort);
//		List<CrudEntity> content = Arrays.asList(new CrudEntity(0, "string_0"), new CrudEntity(1, "string_1"),
//				new CrudEntity(2, "string_2"), new CrudEntity(3, "string_3"), new CrudEntity(4, "string_4"),
//				new CrudEntity(5, "string_5"), new CrudEntity(6, "string_6"), new CrudEntity(7, "string_7"),
//				new CrudEntity(8, "string_8"), new CrudEntity(9, "string_9"));
//		long total = 10L;
//		Page<CrudEntity> entities = new PageImpl<>(content, pageable, total);
//		Mockito.when(crudService.findAll(pageable)).thenReturn(entities);
//		// @formatter:off
//		mockMvc.perform(
//			MockMvcRequestBuilders.get(String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr))
//			.contentType(MediaType.APPLICATION_JSON)
//		)
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.header().string("X-Total-Count", Matchers.is(String.valueOf(total))))
//        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)))
//        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.contains(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)))
//        .andExpect(MockMvcResultMatchers.jsonPath("$[*].string", Matchers.contains("string_0", "string_1", "string_2", "string_3", "string_4", "string_5", "string_6", "string_7", "string_8", "string_9")))
//		.andDo(MockMvcResultHandlers.print());
//		// @formatter:on
//	}
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

	private void delete_no_content() throws IOException, Exception {
		final String NEW_STRING = "new_string";
		String url = String.format("http://localhost:%d/api/cruds/%d", port, ID);
		CrudUpdateCriteria criteria = new CrudUpdateCriteria(NEW_STRING);
		HttpEntity<CrudUpdateCriteria> requestEntity = new HttpEntity<CrudUpdateCriteria>(criteria);
		@SuppressWarnings("unchecked")
		ResponseEntity<CrudEntity> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
				CrudEntity.class, Collections.EMPTY_MAP);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
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
		for(int i = 0; i < TOTAL_COUNT; ++i) {
			Integer id = i;
			String string = "string_" + i;
			CrudEntity entity = new CrudEntity(id, string);
			entities.add(entity);
		}
		return entities;
	}
}
