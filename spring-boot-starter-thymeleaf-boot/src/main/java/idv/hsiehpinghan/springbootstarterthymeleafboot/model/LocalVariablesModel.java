package idv.hsiehpinghan.springbootstarterthymeleafboot.model;

public class LocalVariablesModel {
	private String name;
	private int age;

	public LocalVariablesModel(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "LocalVariablesModel [name=" + name + ", age=" + age + "]";
	}

}
