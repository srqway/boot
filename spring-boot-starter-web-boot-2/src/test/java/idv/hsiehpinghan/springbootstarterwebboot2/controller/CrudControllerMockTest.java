package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.io.IOException;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

//	@Test
	public void test00_create() throws Exception {
		create_created();
		create_conflict();
	}

//	@Test
	public void test01_read() throws Exception {
		read_not_found();
		read_ok();
	}

	@Test
	public void test02_update() throws Exception {
		update_not_found();
		update_ok();
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
		.andExpect(MockMvcResultMatchers.header().string("Location", CoreMatchers.is(String.format("http://localhost/api/cruds/%d", ID))))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(ID)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.string", CoreMatchers.is(NEW_STRING)))
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
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(ID)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.string", CoreMatchers.is(STRING)))
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
		.andExpect(MockMvcResultMatchers.header().string("Location", CoreMatchers.is(String.format("http://localhost/api/cruds/%d", ID))))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(ID)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.string", CoreMatchers.is(STRING)))
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
