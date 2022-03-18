package com.messaging.gateway.api.message.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageRestRequestDto {

    @NotNull(message = "Channel name should not be null")
    private String channelName;

    @NotNull(message = "Message should not be null")
    private String message;

}
