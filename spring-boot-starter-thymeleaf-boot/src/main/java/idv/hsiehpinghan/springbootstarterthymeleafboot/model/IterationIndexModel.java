package idv.hsiehpinghan.springbootstarterthymeleafboot.model;

import java.util.List;
import java.util.Map;

public class IterationIndexModel {
	private List<User> userList;
	private Map<String, Integer> userMap;

	public IterationIndexModel(List<User> userList, Map<String, Integer> userMap) {
		super();
		this.userList = userList;
		this.userMap = userMap;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Map<String, Integer> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, Integer> userMap) {
		this.userMap = userMap;
	}

	@Override
	public String toString() {
		return "IterationIndexModel [userList=" + userList + ", userMap=" + userMap + "]";
	}

	public static class User {
		private String name;
		private int age;

		public User(String name, int age) {
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
			return "User [name=" + name + ", age=" + age + "]";
		}

	}

}
