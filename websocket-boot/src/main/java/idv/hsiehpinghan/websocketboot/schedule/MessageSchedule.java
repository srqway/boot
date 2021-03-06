package idv.hsiehpinghan.websocketboot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.websocketboot.constant.WebsocketbootConstant;
import idv.hsiehpinghan.websocketboot.model.ResponseModel;

@Component
public class MessageSchedule {
	@Autowired
	private SimpMessagingTemplate template;

	@Scheduled(fixedDelay = 3000)
	public void broadcast() {
		ResponseModel model = new ResponseModel("This is broadcast message.");
		template.convertAndSend(WebsocketbootConstant.BROADCAST_TOPIC, model);
	}

}
