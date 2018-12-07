package idv.hsiehpinghan.springrestdocsmockmvcboot.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0")
public class IndexController {

	@GetMapping
	public ResourceSupport index() {
		ResourceSupport index = new ResourceSupport();
		index.add(linkTo(CrudController.class).withRel("cruds"));
		index.add(linkTo(CrudController.class).withRel("files"));
		return index;
	}
}
