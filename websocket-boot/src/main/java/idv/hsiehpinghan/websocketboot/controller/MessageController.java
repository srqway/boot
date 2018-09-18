package idv.hsiehpinghan.websocketboot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.websocketboot.constant.WebsocketbootConstant;
import idv.hsiehpinghan.websocketboot.model.MessageModel;
import idv.hsiehpinghan.websocketboot.model.ResponseModel;

@RestController
@MessageMapping("/message")
public class MessageController {

	@MessageMapping("/to_all")
	@SendTo(WebsocketbootConstant.BROADCAST_TOPIC)
	public ResponseModel toAll(MessageModel model) throws InterruptedException {
		return new ResponseModel("to all message : " + model.getMessage());
	}

	@MessageMapping("/to_user")
	@SendToUser(WebsocketbootConstant.USER_TOPIC)
	public ResponseModel toUser(MessageModel model) throws InterruptedException {
		/**
		 * send to WebsocketbootConstant.USER_TOPIC_PREFIXE + WebsocketbootConstant.USER_0 + WebsocketbootConstant.USER_TOPIC
		 */
		return new ResponseModel("to user message : " + model.getMessage());
	}

}
