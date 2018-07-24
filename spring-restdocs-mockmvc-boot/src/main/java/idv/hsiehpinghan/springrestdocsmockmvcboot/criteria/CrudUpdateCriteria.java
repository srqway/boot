package idv.hsiehpinghan.springrestdocsmockmvcboot.criteria;

public class CrudUpdateCriteria extends CrudCriteria {

	public CrudUpdateCriteria() {
	}

	public CrudUpdateCriteria(Integer id, String string) {
		super(id, string);
	}

	@Override
	public String toString() {
		return "CrudUpdateCriteria [toString()=" + super.toString() + "]";
	}

}