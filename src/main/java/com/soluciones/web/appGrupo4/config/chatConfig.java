package com.soluciones.web.appGrupo4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  //agente de mensajes "mejor manejo para el intercambio"
public class chatConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		registry.addEndpoint("/javatechie").withSockJS();
	
	}// para mejorar el transporte 
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

	    registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	    
	} // 
	
	/**
	 * 
	 * WebSocketMessageBrokerConfigurer // sirve para personalizar la configuración proporcionada
	 *
	 */	
}
