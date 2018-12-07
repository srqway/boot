package idv.hsiehpinghan.springrestdocsmockmvcboot.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import idv.hsiehpinghan.springrestdocsmockmvcboot.constant.Constant;
import idv.hsiehpinghan.springrestdocsmockmvcboot.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springrestdocsmockmvcboot.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springrestdocsmockmvcboot.entity.CrudEntity;
import idv.hsiehpinghan.springrestdocsmockmvcboot.service.CrudService;
import idv.hsiehpinghan.springrestdocsmockmvcboot.utility.ConvertUtility;

@RestController
@RequestMapping(Constant.CRUDS_PATH)
public class CrudController {
	@Autowired
	private CrudService service;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public HttpHeaders create(@RequestBody CrudCreateCriteria criteria) {
		CrudEntity entity = ConvertUtility.convertToCrudEntity(criteria);
		service.save(entity);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(CrudController.class).slash(criteria.getId()).toUri());
		return httpHeaders;
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public CrudEntity read(@PathVariable("id") Integer id) {
		CrudEntity entity = service.findOne(id);
		return entity;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<CrudEntity> read() {
		List<CrudEntity> entities = service.findAll();
		return entities;
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public CrudEntity update(@PathVariable("id") Integer id, @RequestBody CrudUpdateCriteria criteria) {
		String string = criteria.getString();
		CrudEntity entity = new CrudEntity(id, string);
		service.update(entity);
		return entity;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
	}

}