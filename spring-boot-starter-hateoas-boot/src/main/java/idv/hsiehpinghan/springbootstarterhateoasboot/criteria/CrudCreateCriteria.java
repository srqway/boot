package idv.hsiehpinghan.springbootstarterhateoasboot.criteria;

import idv.hsiehpinghan.springbootstarterhateoasboot.criteria.CrudCriteria;

public class CrudCreateCriteria extends CrudCriteria {

	public CrudCreateCriteria() {
		super();
	}

	public CrudCreateCriteria(Integer id, String string) {
		super(id, string);
	}

	@Override
	public String toString() {
		return "CrudCreateCriteria [toString()=" + super.toString() + "]";
	}

}
