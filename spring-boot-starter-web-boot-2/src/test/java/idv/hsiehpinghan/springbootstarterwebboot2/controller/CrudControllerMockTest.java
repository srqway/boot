package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterwebboot2.service.CrudService;

@RunWith(SpringRunner.class)
@WebMvcTest(CrudController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrudControllerMockTest {
	private static final Integer ID = 3;
	private static final String STRING = "string_3";
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	@MockBean
	private CrudService crudService;

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
		List<Order> orders = new LinkedList<>();
		for (int i = 0, sz = properties.size(); i < sz; ++i) {
			Direction direction = directions.get(i);
			String property = properties.get(i);
			Order order = new Order(direction, property, NullHandling.NULLS_LAST);
			orders.add(order);
		}
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(page, size, sort);
		@SuppressWarnings("unchecked")
		List<CrudEntity> content = Collections.EMPTY_LIST;
		long total = 0L;
		Page<CrudEntity> entities = new PageImpl<>(content, pageable, total);
		Mockito.when(crudService.findAll(pageable)).thenReturn(entities);
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.get(String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isNoContent())
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void readAll_partial_content() throws IOException, Exception {
		int page = 1;
		int size = 3;
		List<Direction> directions = Arrays.asList(Direction.ASC, Direction.DESC);
		List<String> properties = Arrays.asList("id", "string");
		String directionsStr = directions.stream().map(x -> "directions=" + x).collect(Collectors.joining("&"));
		String propertiesStr = properties.stream().map(x -> "properties=" + x).collect(Collectors.joining("&"));
		List<Order> orders = new LinkedList<>();
		for (int i = 0, sz = properties.size(); i < sz; ++i) {
			Direction direction = directions.get(i);
			String property = properties.get(i);
			Order order = new Order(direction, property, NullHandling.NULLS_LAST);
			orders.add(order);
		}
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(page, size, sort);
		List<CrudEntity> content = Arrays.asList(new CrudEntity(3, "string_3"), new CrudEntity(4, "string_4"),
				new CrudEntity(5, "string_5"));
		long total = 10L;
		Page<CrudEntity> entities = new PageImpl<>(content, pageable, total);
		Mockito.when(crudService.findAll(pageable)).thenReturn(entities);
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.get(String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isPartialContent())
		.andExpect(MockMvcResultMatchers.header().string("X-Total-Count", Matchers.is(String.valueOf(total))))
		.andExpect(MockMvcResultMatchers.header().string("first", Matchers.is("/api/cruds?page=0&size=3")))
		.andExpect(MockMvcResultMatchers.header().string("last", Matchers.is("/api/cruds?page=3&size=3")))
		.andExpect(MockMvcResultMatchers.header().string("next", Matchers.is("/api/cruds?page=2&size=3")))
		.andExpect(MockMvcResultMatchers.header().string("prev", Matchers.is("/api/cruds?page=0&size=3")))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.contains(3, 4, 5)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].string", Matchers.contains("string_3", "string_4", "string_5")))
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void readAll_ok() throws IOException, Exception {
		int page = 0;
		int size = 10;
		List<Direction> directions = Arrays.asList(Direction.ASC, Direction.DESC);
		List<String> properties = Arrays.asList("id", "string");
		String directionsStr = directions.stream().map(x -> "directions=" + x).collect(Collectors.joining("&"));
		String propertiesStr = properties.stream().map(x -> "properties=" + x).collect(Collectors.joining("&"));
		List<Order> orders = new LinkedList<>();
		for (int i = 0, sz = properties.size(); i < sz; ++i) {
			Direction direction = directions.get(i);
			String property = properties.get(i);
			Order order = new Order(direction, property, NullHandling.NULLS_LAST);
			orders.add(order);
		}
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(page, size, sort);
		List<CrudEntity> content = Arrays.asList(new CrudEntity(0, "string_0"), new CrudEntity(1, "string_1"),
				new CrudEntity(2, "string_2"), new CrudEntity(3, "string_3"), new CrudEntity(4, "string_4"),
				new CrudEntity(5, "string_5"), new CrudEntity(6, "string_6"), new CrudEntity(7, "string_7"),
				new CrudEntity(8, "string_8"), new CrudEntity(9, "string_9"));
		long total = 10L;
		Page<CrudEntity> entities = new PageImpl<>(content, pageable, total);
		Mockito.when(crudService.findAll(pageable)).thenReturn(entities);
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.get(String.format("/api/cruds?page=%d&size=%d&%s&%s", page, size, directionsStr, propertiesStr))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.header().string("X-Total-Count", Matchers.is(String.valueOf(total))))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.contains(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].string", Matchers.contains("string_0", "string_1", "string_2", "string_3", "string_4", "string_5", "string_6", "string_7", "string_8", "string_9")))
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void delete_not_found() throws IOException, Exception {
		Mockito.when(crudService.getOne(ID)).thenReturn(Optional.empty());
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.delete(String.format("/api/cruds/%d", ID))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void delete_no_content() throws IOException, Exception {
		CrudEntity entity = new CrudEntity(ID, STRING);
		Mockito.when(crudService.getOne(ID)).thenReturn(Optional.of(entity));
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.delete(String.format("/api/cruds/%d", ID))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isNoContent())
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void update_not_found() throws IOException, Exception {
		Mockito.when(crudService.getOne(ID)).thenReturn(Optional.empty());
		CrudUpdateCriteria criteria = new CrudUpdateCriteria(STRING);
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.put(String.format("/api/cruds/%d", ID))
			.contentType(MediaType.APPLICATION_JSON)
			.content(convertToJson(criteria))
		)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void update_ok() throws IOException, Exception {
		final String NEW_STRING = "new_string";
		CrudEntity entity = new CrudEntity(ID, STRING);
		Mockito.when(crudService.getOne(ID)).thenReturn(Optional.of(entity));
		CrudUpdateCriteria criteria = new CrudUpdateCriteria(NEW_STRING);
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.put(String.format("/api/cruds/%d", ID))
			.contentType(MediaType.APPLICATION_JSON)
			.content(convertToJson(criteria))
		)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.header().string("Location", Matchers.is(String.format("http://localhost/api/cruds/%d", ID))))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(ID)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.string", Matchers.is(NEW_STRING)))
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void read_not_found() throws IOException, Exception {
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.get(String.format("/api/cruds/%d", ID))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void read_ok() throws IOException, Exception {
		CrudEntity entity = new CrudEntity(ID, STRING);
		Mockito.when(crudService.getOne(ID)).thenReturn(Optional.of(entity));
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.get(String.format("/api/cruds/%d", ID))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(ID)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.string", Matchers.is(STRING)))
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void create_created() throws IOException, Exception {
		Mockito.when(crudService.existsById(ID)).thenReturn(false);
		CrudCreateCriteria createCriteria = new CrudCreateCriteria(ID, STRING);
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/cruds")
			.contentType(MediaType.APPLICATION_JSON)
			.content(convertToJson(createCriteria))
		)
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.header().string("Location", Matchers.is(String.format("http://localhost/api/cruds/%d", ID))))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(ID)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.string", Matchers.is(STRING)))
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private void create_conflict() throws IOException, Exception {
		Mockito.when(crudService.existsById(ID)).thenReturn(true);
		CrudCreateCriteria createCriteria = new CrudCreateCriteria(ID, STRING);
		// @formatter:off
		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/cruds")
			.contentType(MediaType.APPLICATION_JSON)
			.content(convertToJson(createCriteria))
		)
		.andExpect(MockMvcResultMatchers.status().isConflict())
		.andDo(MockMvcResultHandlers.print());
		// @formatter:on
	}

	private String convertToJson(Object object) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
