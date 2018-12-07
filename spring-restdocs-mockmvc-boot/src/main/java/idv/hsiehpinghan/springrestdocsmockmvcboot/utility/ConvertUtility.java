package idv.hsiehpinghan.springrestdocsmockmvcboot.utility;

import idv.hsiehpinghan.springrestdocsmockmvcboot.criteria.CrudCreateCriteria;
import idv.hsiehpinghan.springrestdocsmockmvcboot.entity.CrudEntity;

public class ConvertUtility {
	public static CrudEntity convertToCrudEntity(CrudCreateCriteria criteria) {
		Integer id = criteria.getId();
		String string = criteria.getString();
		CrudEntity entity = new CrudEntity(id, string);
		return entity;
	}
}
