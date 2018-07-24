package idv.hsiehpinghan.springbootstarterthymeleafboot.criteria;

public class LinkUrlsCriteria {
	private String name;
	private int age;

	public LinkUrlsCriteria() {
		super();
	}

	public LinkUrlsCriteria(String name, int age) {
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
}
