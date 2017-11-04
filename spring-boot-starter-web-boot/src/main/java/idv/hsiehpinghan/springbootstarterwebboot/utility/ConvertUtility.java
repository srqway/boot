package idv.hsiehpinghan.springbootstarterwebboot.utility;

import idv.hsiehpinghan.springbootstarterwebboot.criteria.CrudCriteria;
import idv.hsiehpinghan.springbootstarterwebboot.entity.CrudEntity;

public class ConvertUtility {
	public static CrudEntity convertToCrudEntity(CrudCriteria criteria) {
		Integer id = criteria.getId();
		String string = criteria.getString();
		CrudEntity entity = new CrudEntity(id, string);
		return entity;
	}
}
