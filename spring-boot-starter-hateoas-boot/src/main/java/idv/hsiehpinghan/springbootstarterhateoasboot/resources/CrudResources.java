package idv.hsiehpinghan.springbootstarterhateoasboot.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

import idv.hsiehpinghan.springbootstarterhateoasboot.controller.CrudController;
import idv.hsiehpinghan.springbootstarterhateoasboot.resource.CrudResource;

public class CrudResources extends Resources<CrudResource> {

	public CrudResources(Iterable<CrudResource> iterable, Link... links) {
		super(iterable, links);
		add(linkTo(CrudController.class).withSelfRel());
	}

}