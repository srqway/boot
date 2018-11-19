package idv.hsiehpinghan.springbootstarterhateoasboot.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import idv.hsiehpinghan.springbootstarterhateoasboot.controller.CrudController;
import idv.hsiehpinghan.springbootstarterhateoasboot.model.CrudModel;

public class CrudResource extends ResourceSupport {
	private CrudModel model;

	public CrudResource(CrudModel model) {
		super();
		this.model = model;
		add(linkTo(methodOn(CrudController.class).read(this.model.getId())).withSelfRel());
		add(linkTo(CrudController.class).withRel("all"));
	}

	public CrudModel getModel() {
		return model;
	}

	public void setModel(CrudModel model) {
		this.model = model;
	}

}