package idv.hsiehpinghan.springbootstarterhateoasboot.controller;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.springbootstarterhateoasboot.resource.ListAllFormsOfMetadataResource;

@RestController
@RequestMapping(value = { "/profile" })
public class ProfileController {

	@GetMapping(produces = { "application/hal+json" })
	public HttpEntity<ResourceSupport> listAllFormsOfMetadata() {
		ListAllFormsOfMetadataResource resource = new ListAllFormsOfMetadataResource();
		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(linkTo(ProfileController.class).slash(resource).toUri());
		// return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

}
