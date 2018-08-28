package idv.hsiehpinghan.websocketboot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import idv.hsiehpinghan.websocketboot.constant.WebsocketbootConstant;
import idv.hsiehpinghan.websocketboot.model.RequestModel;
import idv.hsiehpinghan.websocketboot.model.ResponseModel;

@Controller
@MessageMapping("/message")
public class MessageController {

	@MessageMapping("/request")
	@SendTo(WebsocketbootConstant.TOPIC + "/response")
	public ResponseModel request(RequestModel requestModel) throws InterruptedException {
		return new ResponseModel("Hi! " + requestModel.getName() + ".");
	}

}
