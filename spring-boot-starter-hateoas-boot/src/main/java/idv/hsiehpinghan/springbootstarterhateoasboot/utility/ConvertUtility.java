package idv.hsiehpinghan.springbootstarterhateoasboot.utility;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resources;

import idv.hsiehpinghan.springbootstarterhateoasboot.controller.CrudController;
import idv.hsiehpinghan.springbootstarterhateoasboot.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterhateoasboot.resource.CrudModel;
import idv.hsiehpinghan.springbootstarterhateoasboot.resource.CrudResource;

public class ConvertUtility {

	public static CrudResource toCrudResource(CrudEntity entity) {
		CrudModel model = toCrudModel(entity);
		CrudResource resource = new CrudResource(model);
		return resource;
	}

	public static Resources<CrudResource> addLinks(Resources<CrudResource> resources) {
		resources.add(linkTo(CrudController.class).withSelfRel());
		return resources;
	}

	private static CrudModel toCrudModel(CrudEntity entity) {
		Integer id = entity.getId();
		String string = entity.getString();
		CrudModel model = new CrudModel(id, string);
		return model;
	}
}
