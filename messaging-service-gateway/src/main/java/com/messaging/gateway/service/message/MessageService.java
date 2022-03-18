package com.messaging.gateway.service.message;

import com.messaging.gateway.service.message.model.request.CreateMessageRequest;
import com.messaging.gateway.service.message.model.response.CreateMessageResponse;

public interface MessageService {
    CreateMessageResponse createMessageForChannel(final CreateMessageRequest request);
}
