package idv.hsiehpinghan.springbootstarterthymeleafboot.model;

import java.util.Date;
import java.util.List;

import idv.hsiehpinghan.springbootstarterthymeleafboot.enumeration.Enumeration;

public class BasicModel {
	private String string_;
	private Date date_;
	private String html_;
	private List<String> strings_;
	private boolean boolean_;
	private Enumeration enumeration_;

	public BasicModel(String string_, Date date_, String html_, List<String> strings_, boolean boolean_,
			Enumeration enumeration_) {
		super();
		this.string_ = string_;
		this.date_ = date_;
		this.html_ = html_;
		this.strings_ = strings_;
		this.boolean_ = boolean_;
		this.enumeration_ = enumeration_;
	}

	public String getString_() {
		return string_;
	}

	public void setString_(String string_) {
		this.string_ = string_;
	}

	public Date getDate_() {
		return date_;
	}

	public void setDate_(Date date_) {
		this.date_ = date_;
	}

	public String getHtml_() {
		return html_;
	}

	public void setHtml_(String html_) {
		this.html_ = html_;
	}

	public List<String> getStrings_() {
		return strings_;
	}

	public void setStrings_(List<String> strings_) {
		this.strings_ = strings_;
	}

	public boolean isBoolean_() {
		return boolean_;
	}

	public void setBoolean_(boolean boolean_) {
		this.boolean_ = boolean_;
	}

	public Enumeration getEnumeration_() {
		return enumeration_;
	}

	public void setEnumeration_(Enumeration enumeration_) {
		this.enumeration_ = enumeration_;
	}

}
