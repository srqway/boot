package idv.hsiehpinghan.springbootstarterthymeleafboot.model;

import java.util.Collection;

public class VarialesModel {
	private String variable;
	private Collection<String> collection;

	public VarialesModel(String variable, Collection<String> collection) {
		super();
		this.variable = variable;
		this.collection = collection;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Collection<String> getCollection() {
		return collection;
	}

	public void setCollection(Collection<String> collection) {
		this.collection = collection;
	}

	public String generateString(String str) {
		return String.format("generate string : %s", str);
	}
}
