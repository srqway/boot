package idv.hsiehpinghan.springbootstarterwebboot2.criteria;

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
