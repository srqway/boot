package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterwebboot2.service.CrudService;

@RunWith(SpringRunner.class)
@WebMvcTest(CrudController.class)
public class CrudControllerMockTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	@MockBean
	private CrudService crudService;

	@Test
	public void create() throws Exception {
		create_created();
		create_conflict();
	}

	private void create_created() throws IOException, Exception {
		Integer id = 3;
		String string = "string_3";
		Mockito.when(crudService.existsById(id)).thenReturn(false);
		CrudEntity entity = new CrudEntity(id, string);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/cruds").contentType(MediaType.APPLICATION_JSON).content(convertToJson(entity)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.header().string("Location",
						CoreMatchers.is("http://localhost/api/cruds/3")))
				.andExpect(MockMvcResultMatchers.content().string("")).andDo(MockMvcResultHandlers.print());
	}

	private void create_conflict() throws IOException, Exception {
		Integer id = 3;
		String string = "string_3";
		Mockito.when(crudService.existsById(id)).thenReturn(true);
		CrudEntity entity = new CrudEntity(id, string);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/cruds").contentType(MediaType.APPLICATION_JSON)
				.content(convertToJson(entity))).andExpect(MockMvcResultMatchers.status().isConflict())
				.andDo(MockMvcResultHandlers.print());
	}

	private String convertToJson(Object object) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
