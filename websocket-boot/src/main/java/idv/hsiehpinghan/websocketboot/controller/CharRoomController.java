package idv.hsiehpinghan.websocketboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import idv.hsiehpinghan.websocketboot.model.ChatClientModel;
import idv.hsiehpinghan.websocketboot.model.ServerResponseModel;

@Controller
public class CharRoomController {
	@MessageMapping("/messageControl")
	@SendTo("/destination_0/getResponse")
	public ServerResponseModel said(ChatClientModel responseMessage) throws InterruptedException {
		return new ServerResponseModel("歡迎來到," + responseMessage.getName());
	}
	
    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=3000)
    public void publishUpdates(){
    	ServerResponseModel model = new ServerResponseModel("hah");
        template.convertAndSend("/destination_0/getResponse", model);
    }
}
