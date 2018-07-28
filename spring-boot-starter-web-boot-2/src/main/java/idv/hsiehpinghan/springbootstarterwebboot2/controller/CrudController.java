package idv.hsiehpinghan.springbootstarterwebboot2.controller;

import java.util.Optional;

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
import org.springframework.web.util.UriComponentsBuilder;

import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.criteria.CrudUpdateCriteria;
import idv.hsiehpinghan.springbootstarterwebboot2.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterwebboot2.service.CrudService;

@RestController
@RequestMapping("/api/cruds")
public class CrudController {
//	private Set<CrudEntity> entities = new HashSet<>();
//	https://dzone.com/articles/leverage-http-status-codes-to-build-a-rest-service

	private CrudService crudService;

	public CrudController(CrudService crudService) {
		super();
		this.crudService = crudService;
	}


	@PostMapping
	public ResponseEntity<CrudEntity> create(@RequestBody CrudCreateCriteria criteria, UriComponentsBuilder uriComponentsBuilder) {
		Integer id = criteria.getId();
		String string = criteria.getString();
		if(crudService.existsById(id) == true) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		CrudEntity entity = new CrudEntity(id, string);
		crudService.save(entity);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(uriComponentsBuilder.path("/api/cruds/{id}").buildAndExpand(id).toUri());
	    return new ResponseEntity<>(entity, headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CrudEntity> read(@PathVariable("id") Integer id) {
		Optional<CrudEntity> entityOption = crudService.getOne(id);
		if(entityOption.isPresent() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entityOption.get(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CrudEntity> update(@PathVariable("id") Integer id, @RequestBody CrudUpdateCriteria criteria, UriComponentsBuilder uriComponentsBuilder) {
		Optional<CrudEntity> entityOption = crudService.getOne(id);
		if(entityOption.isPresent() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String string = criteria.getString();
		CrudEntity entity = entityOption.get();
		entity.setString(string);
		crudService.update(entity);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(uriComponentsBuilder.path("/api/cruds/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<>(entity, headers, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		Optional<CrudEntity> entityOption = crudService.getOne(id);
		if(entityOption.isPresent() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		crudService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
//	@DeleteMapping("/{isbn}")
//
//	public ResponseEntity<?> deleteBook(@PathVariable("isbn") String isbn) {
//
//	    return bookRepository.findByIsbn(isbn)
//
//	        .map(book -> {
//
//	            bookRepository.delete(book);
//
//	            return new ResponseEntity(HttpStatus.NO_CONTENT);
//
//	         })
//
//	         .orElseThrow(() -> new BookNotFoundException(isbn));
//
//	}
	
	
//	@GetMapping(value = "/")
//	public ResponseEntity<Set<CrudEntity>> readAll(@RequestBody CrudReadCriteria criteria, UriComponentsBuilder uriComponentsBuilder) {
//		int pageSize = criteria.getPageSize();
//		if(entities.size() <= 0) {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//		String firstUri = uriComponentsBuilder.path("/cruds?{id}").buildAndExpand(id).toUri();
//		
//        long totalBooks = booksPage.getTotalElements();
//        int nbPageBooks = booksPage.getNumberOfElements();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Total-Count", String.valueOf(totalBooks));
//        if (nbPageBooks < totalBooks) {
//            headers.add("first", buildPageUri(PageRequest.of(0, booksPage.getSize())));
//            headers.add("last", buildPageUri(PageRequest.of(booksPage.getTotalPages() - 1, booksPage.getSize())));
//            if (booksPage.hasNext()) {
//                headers.add("next", buildPageUri(booksPage.nextPageable()));
//            }
//            if (booksPage.hasPrevious()) {
//                headers.add("prev", buildPageUri(booksPage.previousPageable()));
//            }
//            return new ResponseEntity<>(booksPage.getContent(), headers, HttpStatus.PARTIAL_CONTENT);
//        } else {
//            return new ResponseEntity(booksPage.getContent(), headers, HttpStatus.OK);
//        }
//        
//        
//		
//		
//		List<CrudEntity> entities = service.findAll();
//		return entities;
//	}
//
//	@GetMapping
//	public ResponseEntity<List<Book>> getAllBooks(
//	    @PageableDefault(size = MAX_PAGE_SIZE) Pageable pageable,
//	    @RequestParam(required = false, defaultValue = "id") String sort,
//	    @RequestParam(required = false, defaultValue = "asc") String order) {
//	    final PageRequest pr = PageRequest.of(
//	        pageable.getPageNumber(), pageable.getPageSize(),
//	            Sort.by("asc" .equals(order) ? Sort.Direction.ASC : Sort.Direction.DESC, sort)
//	    );
//	    Page<Book> booksPage = bookRepository.findAll(pr);
//	    if (booksPage.getContent().isEmpty()) {
//	        return new ResponseEntity(HttpStatus.NO_CONTENT);
//	    } else {
//	        long totalBooks = booksPage.getTotalElements();
//	        int nbPageBooks = booksPage.getNumberOfElements();
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.add("X-Total-Count", String.valueOf(totalBooks));
//	        if (nbPageBooks < totalBooks) {
//	            headers.add("first", buildPageUri(PageRequest.of(0, booksPage.getSize())));
//	            headers.add("last", buildPageUri(PageRequest.of(booksPage.getTotalPages() - 1, booksPage.getSize())));
//	            if (booksPage.hasNext()) {
//	                headers.add("next", buildPageUri(booksPage.nextPageable()));
//	            }
//	            if (booksPage.hasPrevious()) {
//	                headers.add("prev", buildPageUri(booksPage.previousPageable()));
//	            }
//	            return new ResponseEntity<>(booksPage.getContent(), headers, HttpStatus.PARTIAL_CONTENT);
//	        } else {
//	            return new ResponseEntity(booksPage.getContent(), headers, HttpStatus.OK);
//	        }
//	    }
//	}
//	

//
//	@PatchMapping(value = "/{id}", consumes = "application/json")
//	public ResponseEntity<CrudEntity> partialUpdate(@PathVariable("id") Integer id, @RequestBody CrudUpdateCriteria criteria) {
//		CrudEntity entity = getOne(id);
//		if(entity == null) {
//			new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		String string = criteria.getString();
//		entity.setString(string);
//		return new ResponseEntity<>(entity, HttpStatus.OK);
//	}

//	
//	private CrudEntity getOne(Integer id) {
//		for(CrudEntity entity : entities) {
//			if(entity.getId().equals(id) == true) {
//				return entity;
//			}
//		}
//		return null;
//	}
}
