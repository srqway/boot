package idv.hsiehpinghan.springrestdocsmockmvcboot.criteria;

public class CrudUpdateCriteria {
	private String string;

	public CrudUpdateCriteria() {
	}

	public CrudUpdateCriteria(String string) {
		super();
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}