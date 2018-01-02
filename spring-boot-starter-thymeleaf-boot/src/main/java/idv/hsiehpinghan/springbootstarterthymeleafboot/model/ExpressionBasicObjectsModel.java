package idv.hsiehpinghan.springbootstarterthymeleafboot.model;

public class ExpressionBasicObjectsModel {
	private String variable;

	public ExpressionBasicObjectsModel(String variable) {
		super();
		this.variable = variable;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "ExpressionBasicObjectsModel [variable=" + variable + "]";
	}

}
