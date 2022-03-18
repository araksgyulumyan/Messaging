package com.messaging.gateway.api.message.facade;

import com.messaging.gateway.api.message.dto.request.CreateMessageRestRequestDto;
import com.messaging.gateway.api.message.dto.response.CreateMessageRestResponseDto;

public interface MessageApiFacade {
    CreateMessageRestResponseDto createMessage(CreateMessageRestRequestDto requestDto);
}
