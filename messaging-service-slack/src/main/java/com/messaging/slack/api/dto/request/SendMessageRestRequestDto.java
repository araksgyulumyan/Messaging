package com.messaging.slack.api.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SendMessageRestRequestDto {

    @NotNull(message = "Channel name should not be null")
    private String channelName;

    @NotNull(message = "Message should not be null")
    private String message;
}
