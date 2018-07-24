package idv.hsiehpinghan.springrestdocsmockmvcboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.springrestdocsmockmvcboot.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springrestdocsmockmvcboot.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springrestdocsmockmvcboot.entity.CrudEntity;
import idv.hsiehpinghan.springrestdocsmockmvcboot.service.CrudService;
import idv.hsiehpinghan.springrestdocsmockmvcboot.utility.ConvertUtility;

@RestController
@RequestMapping("/crud")
public class CrudController {
	private final String NO_CONTENT = "no content";
	@Autowired
	private CrudService service;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody CrudCreateCriteria criteria) {
		CrudEntity entity = ConvertUtility.convertToCrudEntity(criteria);
		service.save(entity);
	}

	@GetMapping(value = "/read/{id}")
	public CrudEntity readId(@PathVariable("id") Integer id) {
		CrudEntity entity = service.findOne(id);
		return entity;
	}

	@GetMapping(value = "/read")
	public List<CrudEntity> read() {
		List<CrudEntity> entities = service.findAll();
		return entities;
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public String update(@RequestBody CrudUpdateCriteria criteria) {
		CrudEntity entity = ConvertUtility.convertToCrudEntity(criteria);
		service.update(entity);
		return NO_CONTENT;
	}

	@DeleteMapping(value = "/delete/{id}")
	public String deleteId(@PathVariable("id") Integer id) {
		service.delete(id);
		return NO_CONTENT;
	}

}