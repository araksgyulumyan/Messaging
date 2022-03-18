package com.messaging.gateway.client.slack.request;

import lombok.Data;

@Data
public class SendMessageClientRequest {
    private final String channelName;
    private final String message;
}
