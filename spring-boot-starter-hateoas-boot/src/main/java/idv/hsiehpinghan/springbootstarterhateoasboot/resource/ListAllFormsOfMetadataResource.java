package idv.hsiehpinghan.springbootstarterhateoasboot.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.ResourceSupport;

import idv.hsiehpinghan.springbootstarterhateoasboot.controller.CrudController;
import idv.hsiehpinghan.springbootstarterhateoasboot.controller.ProfileController;

public class ListAllFormsOfMetadataResource extends ResourceSupport {

	public ListAllFormsOfMetadataResource() {
		super();
		add(linkTo(ProfileController.class).withSelfRel());
		add(linkTo(CrudController.class).withRel("crud"));
	}

}