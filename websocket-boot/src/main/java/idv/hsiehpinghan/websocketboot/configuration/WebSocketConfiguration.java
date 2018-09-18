package idv.hsiehpinghan.websocketboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import idv.hsiehpinghan.websocketboot.constant.WebsocketbootConstant;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		stompEndpointRegistry.addEndpoint(WebsocketbootConstant.ENDPOINT_0).setAllowedOrigins("*").withSockJS()
				.setClientLibraryUrl("/webjars/sockjs-client/1.0.2/sockjs.min.js");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
		messageBrokerRegistry.enableSimpleBroker(WebsocketbootConstant.BROADCAST_TOPIC_PREFIXE,
				WebsocketbootConstant.USER_TOPIC_PREFIXE);
		messageBrokerRegistry.setApplicationDestinationPrefixes(WebsocketbootConstant.APP_TOPIC_PREFIXE);
		messageBrokerRegistry.setUserDestinationPrefix(WebsocketbootConstant.USER_TOPIC_PREFIXE);
	}

}
