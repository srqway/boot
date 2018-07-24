package idv.hsiehpinghan.springbootstarterthymeleafboot.model;

import idv.hsiehpinghan.springbootstarterthymeleafboot.enumeration.Enumeration;

public class TextModel {
	private Enumeration enumeration;
	private String html;
	private String myProperty;

	public TextModel(Enumeration enumeration, String html, String myProperty) {
		super();
		this.enumeration = enumeration;
		this.html = html;
		this.myProperty = myProperty;
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getMyProperty() {
		return myProperty;
	}

	public void setMyProperty(String myProperty) {
		this.myProperty = myProperty;
	}

}
