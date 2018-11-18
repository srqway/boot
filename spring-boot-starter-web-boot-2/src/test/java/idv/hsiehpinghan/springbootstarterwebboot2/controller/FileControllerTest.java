package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.io.IOException;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import idv.hsiehpinghan.springbootstarterwebboot2.service.FileService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FileControllerTest {
	private final String FILE_NAME = "application.yml";
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private FileService service;

	@Test
	public void test00_init() throws Exception {
		service.delete(FILE_NAME);
	}

	@Test
	public void test01_store() throws Exception {
		store_stored();
		store_conflict();
	}

	@Test
	public void test02_load() throws Exception {
		load_not_found();
		load_ok();
	}

	@Test
	public void test03_delete() throws Exception {
		delete_not_found();
		delete_no_content();
	}

	private void delete_not_found() throws IOException, Exception {
		String url = String.format("http://localhost:%d/api/files/%s", port, "NOT_EXIST_FILE_NAME");
		ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
	}

	private void delete_no_content() throws IOException, Exception {
		String url = String.format("http://localhost:%d/api/files/%s", port, FILE_NAME);
		ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
	}

	private void load_not_found() {
		String url = String.format("http://localhost:%d/api/files/%s", port, "NOT_EXIST_FILE_NAME");
		ResponseEntity<Resource> responseEntity = restTemplate.getForEntity(url, Resource.class);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
		Assertions.assertThat(responseEntity.getBody()).isNull();
	}

	private void load_ok() throws IOException {
		String url = String.format("http://localhost:%d/api/files/%s", port, FILE_NAME);
		ResponseEntity<Resource> responseEntity = restTemplate.getForEntity(url, Resource.class);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getHeaders().get("Content-Disposition"))
				.contains(String.format("attachment; filename=\"%s\"", FILE_NAME));
		ClassPathResource resource = new ClassPathResource(FILE_NAME);
		Assertions.assertThat(responseEntity.getBody().getInputStream()).hasSameContentAs(resource.getInputStream());
	}

	private void store_stored() {
		String url = String.format("http://localhost:%d/api/files/", port);
		ClassPathResource resource = new ClassPathResource(FILE_NAME);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("file", resource);
		ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, map, Void.class);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
		Assertions.assertThat(responseEntity.getHeaders().get("Location"))
				.contains(String.format("http://localhost:%d/api/files/%s", port, resource.getFilename()));
	}

	private void store_conflict() {
		String url = String.format("http://localhost:%d/api/files/", port);
		ClassPathResource resource = new ClassPathResource(FILE_NAME);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("file", resource);
		ResponseEntity<Void> responseEntity = restTemplate.postForEntity(url, map, Void.class);
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CONFLICT);
		Assertions.assertThat(responseEntity.getBody()).isNull();
	}

}
