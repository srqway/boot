package idv.hsiehpinghan.websocketboot.model;

public class ResponseModel {
	private String messages;

	public ResponseModel(String messages) {
		super();
		this.messages = messages;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

}