package idv.hsiehpinghan.springcloudconfigclientboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.springcloudconfigclientboot.config.ConfigServerConfig;
import idv.hsiehpinghan.springcloudconfigclientboot.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springcloudconfigclientboot.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springcloudconfigclientboot.entity.CrudEntity;
import idv.hsiehpinghan.springcloudconfigclientboot.service.CrudService;
import idv.hsiehpinghan.springcloudconfigclientboot.utility.ConvertUtility;

@RestController
@RequestMapping("/crud")
public class CrudController {
	private final String NO_CONTENT = "no content";
	@Autowired
	private ConfigServerConfig configServerConfig;
	@Autowired
	private CrudService service;

	@PostMapping(value = "/create", consumes = "application/json")
	public String create(@RequestBody CrudCreateCriteria criteria) {
		CrudEntity entity = ConvertUtility.convertToCrudEntity(criteria, configServerConfig);
		service.save(entity);
		return NO_CONTENT;
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
		CrudEntity entity = ConvertUtility.convertToCrudEntity(criteria, configServerConfig);
		service.update(entity);
		return NO_CONTENT;
	}

	@DeleteMapping(value = "/delete/{id}")
	public String deleteId(@PathVariable("id") Integer id) {
		service.delete(id);
		return NO_CONTENT;
	}

}
