package com.app.crud.config;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Component
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public static final String MESSAGE_PREFIX = "/topic";


    @Override
    @CrossOrigin
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/crudApp").withSockJS();
    }

    @Override
    @CrossOrigin
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay(MESSAGE_PREFIX)
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        registry.setApplicationDestinationPrefixes("/app");
    }




}
