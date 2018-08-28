package idv.hsiehpinghan.websocketboot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import idv.hsiehpinghan.websocketboot.constant.WebsocketbootConstant;
import idv.hsiehpinghan.websocketboot.model.ChatClientModel;
import idv.hsiehpinghan.websocketboot.model.ServerResponseModel;

@Controller
@MessageMapping("/message")
public class MessageController {

	@MessageMapping("/request")
	@SendTo(WebsocketbootConstant.TOPIC + "/response")
	public ServerResponseModel request(ChatClientModel responseMessage) throws InterruptedException {
		return new ServerResponseModel("歡迎來到," + responseMessage.getName());
	}

}
