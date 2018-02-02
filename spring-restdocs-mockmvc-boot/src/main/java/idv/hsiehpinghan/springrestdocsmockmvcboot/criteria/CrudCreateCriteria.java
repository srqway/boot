package idv.hsiehpinghan.springrestdocsmockmvcboot.criteria;

public class CrudCreateCriteria extends CrudCriteria {

	public CrudCreateCriteria() {
	}

	public CrudCreateCriteria(Integer id, String string) {
		super(id, string);
	}

	@Override
	public String toString() {
		return "CrudCreateCriteria [toString()=" + super.toString() + "]";
	}

}