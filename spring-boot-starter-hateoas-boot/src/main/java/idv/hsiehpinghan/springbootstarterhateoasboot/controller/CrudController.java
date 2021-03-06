package idv.hsiehpinghan.springbootstarterhateoasboot.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.springbootstarterhateoasboot.collector.ResourcesCollector;
import idv.hsiehpinghan.springbootstarterhateoasboot.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springbootstarterhateoasboot.criteria.CrudReadAllCriteria;
import idv.hsiehpinghan.springbootstarterhateoasboot.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springbootstarterhateoasboot.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterhateoasboot.resource.CrudResource;
import idv.hsiehpinghan.springbootstarterhateoasboot.resources.CrudResources;
import idv.hsiehpinghan.springbootstarterhateoasboot.service.CrudService;
import idv.hsiehpinghan.springbootstarterhateoasboot.utility.ConvertUtility;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping(value = { "/api/cruds" }, produces = { "application/hal+json" })
public class CrudController {
	private CrudService crudService;

	public CrudController(CrudService crudService) {
		super();
		this.crudService = crudService;
	}

	@PostMapping
	public ResponseEntity<CrudResource> create(@RequestBody CrudCreateCriteria criteria) {
		Integer id = criteria.getId();
		String string = criteria.getString();
		if (crudService.existsById(id) == true) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		CrudEntity entity = new CrudEntity(id, string);
		crudService.save(entity);
		CrudResource resource = ConvertUtility.toCrudResource(entity);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(linkTo(CrudController.class).slash(resource).toUri());
		return new ResponseEntity<>(resource, headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CrudResource> read(@PathVariable("id") Integer id) {
		Optional<CrudEntity> entityOption = crudService.getOne(id);
		if (entityOption.isPresent() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ConvertUtility.toCrudResource(entityOption.get()), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CrudResource> update(@PathVariable("id") Integer id, @RequestBody CrudUpdateCriteria criteria) {
		Optional<CrudEntity> entityOption = crudService.getOne(id);
		if (entityOption.isPresent() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String string = criteria.getString();
		CrudEntity entity = entityOption.get();
		entity.setString(string);
		crudService.update(entity);
		CrudResource resource = ConvertUtility.toCrudResource(entity);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(linkTo(CrudController.class).slash(resource).toUri());
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		Optional<CrudEntity> entityOption = crudService.getOne(id);
		if (entityOption.isPresent() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		crudService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<Resources<CrudResource>> readAll(CrudReadAllCriteria criteria) {
		int page = criteria.getPage();
		int size = criteria.getSize();
		List<Direction> directions = criteria.getDirections();
		List<String> properties = criteria.getProperties();
		List<Order> orders = new LinkedList<>();
		for (int i = 0, sz = properties.size(); i < sz; ++i) {
			Direction direction = directions.get(i);
			String property = properties.get(i);
			Order order = new Order(direction, property, NullHandling.NULLS_LAST);
			orders.add(order);
		}
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<CrudEntity> entities = crudService.findAll(pageable);
		if (entities.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			long totalCount = entities.getTotalElements();
			int currentCount = entities.getNumberOfElements();
			HttpHeaders headers = new HttpHeaders();
			headers.add("X-Total-Count", String.valueOf(totalCount));
			if (currentCount < totalCount) {
				headers.add("first", generatePageUri(PageRequest.of(0, entities.getSize())));
				headers.add("last", generatePageUri(PageRequest.of(entities.getTotalPages() - 1, entities.getSize())));
				if (entities.hasNext()) {
					headers.add("next", generatePageUri(entities.nextPageable()));
				}
				if (entities.hasPrevious()) {
					headers.add("prev", generatePageUri(entities.previousPageable()));
				}
				CrudResources resources = entities.getContent().stream().map(ConvertUtility::toCrudResource)
						.collect(new ResourcesCollector());
				return new ResponseEntity<>(resources, headers, HttpStatus.PARTIAL_CONTENT);
			} else {
				CrudResources resources = entities.getContent().stream().map(ConvertUtility::toCrudResource)
						.collect(new ResourcesCollector());
				return new ResponseEntity<>(resources, headers, HttpStatus.OK);
			}
		}
	}

	private String generatePageUri(Pageable page) {
		return linkTo(CrudController.class).toUriComponentsBuilder().query("page={page}&size={size}")
				.buildAndExpand(page.getPageNumber(), page.getPageSize()).toUriString();
	}

}
