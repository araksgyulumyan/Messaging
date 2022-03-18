package com.messaging.slack.client.model;

import lombok.Data;

@Data
public class SendMessageRequest {
    private final String channelName;
    private final String message;
}
