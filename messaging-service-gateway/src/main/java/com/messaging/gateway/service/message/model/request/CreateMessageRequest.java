package com.messaging.gateway.service.message.model.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

    private final String channelName;
    private final String message;
}
