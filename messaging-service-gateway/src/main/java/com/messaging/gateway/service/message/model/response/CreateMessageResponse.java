package com.messaging.gateway.service.message.model.response;


import lombok.Data;

@Data
public class CreateMessageResponse {
    private final String channelName;
    private final String message;
}
