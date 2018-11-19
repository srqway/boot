package idv.hsiehpinghan.springbootstarterhateoasboot.utility;

import idv.hsiehpinghan.springbootstarterhateoasboot.entity.CrudEntity;
import idv.hsiehpinghan.springbootstarterhateoasboot.model.CrudModel;
import idv.hsiehpinghan.springbootstarterhateoasboot.resource.CrudResource;

public class ConvertUtility {

	public static CrudResource toCrudResource(CrudEntity entity) {
		CrudModel model = toCrudModel(entity);
		CrudResource resource = new CrudResource(model);
		return resource;
	}

	private static CrudModel toCrudModel(CrudEntity entity) {
		Integer id = entity.getId();
		String string = entity.getString();
		CrudModel model = new CrudModel(id, string);
		return model;
	}
}
