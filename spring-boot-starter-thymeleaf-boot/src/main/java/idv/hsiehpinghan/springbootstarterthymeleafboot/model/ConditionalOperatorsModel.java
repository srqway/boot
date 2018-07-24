package idv.hsiehpinghan.springbootstarterthymeleafboot.model;

public class ConditionalOperatorsModel {
	private Boolean true_;
	private Boolean false_;
	private String null_;

	public ConditionalOperatorsModel(Boolean true_, Boolean false_, String null_) {
		super();
		this.true_ = true_;
		this.false_ = false_;
		this.null_ = null_;
	}

	public Boolean getTrue_() {
		return true_;
	}

	public void setTrue_(Boolean true_) {
		this.true_ = true_;
	}

	public Boolean getFalse_() {
		return false_;
	}

	public void setFalse_(Boolean false_) {
		this.false_ = false_;
	}

	public String getNull_() {
		return null_;
	}

	public void setNull_(String null_) {
		this.null_ = null_;
	}

}
