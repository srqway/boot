package idv.hsiehpinghan.springbootstarterhateoasboot.model;

public class CrudModel {
	private Integer id;
	private String string;

	public CrudModel(Integer id, String string) {
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