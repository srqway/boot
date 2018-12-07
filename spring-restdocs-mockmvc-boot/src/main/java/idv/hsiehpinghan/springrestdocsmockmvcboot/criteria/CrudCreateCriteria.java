package idv.hsiehpinghan.springrestdocsmockmvcboot.criteria;

public class CrudCreateCriteria {
	private Integer id;
	private String string;

	public CrudCreateCriteria() {
	}

	public CrudCreateCriteria(Integer id, String string) {
		super();
		this.id = id;
		this.string = string;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}