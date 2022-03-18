package com.messaging.gateway.api.message.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRestResponseDto {

    @JsonProperty("channelName")
    private String channelName;

    @JsonProperty("message")
    private String message;
}
