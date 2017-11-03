package idv.hsiehpinghan.springkafkaboot.model;

public class JsonModel {
	private String string;

	public JsonModel() {
		super();
	}

	public JsonModel(String string) {
		super();
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "JsonModel [string=" + string + "]";
	}

}
