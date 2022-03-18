package com.messaging.slack.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetMessageRestResponseDto {

    @JsonProperty("channelName")
    private final String channelName;

    @JsonProperty("message")
    private final String message;
}
