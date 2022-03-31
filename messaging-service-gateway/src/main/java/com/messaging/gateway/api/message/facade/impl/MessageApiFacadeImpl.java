package com.messaging.gateway.api.message.facade.impl;

import com.messaging.gateway.api.message.dto.request.CreateMessageRestRequestDto;
import com.messaging.gateway.api.message.dto.response.CreateMessageRestResponseDto;
import com.messaging.gateway.api.message.facade.MessageApiFacade;
import com.messaging.gateway.client.slack.SlackMessagingServiceClient;
import com.messaging.gateway.client.slack.request.SendMessageClientRequest;
import com.messaging.gateway.service.message.MessageService;
import com.messaging.gateway.service.message.model.request.CreateMessageRequest;
import com.messaging.gateway.service.message.model.response.CreateMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MessageApiFacadeImpl implements MessageApiFacade {

    private final SlackMessagingServiceClient slackMessagingServiceClient;
    private final MessageService messageService;

    @Override
    public CreateMessageRestResponseDto createMessage(CreateMessageRestRequestDto requestDto) {
        var createMessageResponse = messageService.createMessageForChannel(f.apply(requestDto));
        slackMessagingServiceClient.sendMessage(new SendMessageClientRequest(requestDto.getChannelName(), requestDto.getMessage()));
        return new CreateMessageRestResponseDto(createMessageResponse.getChannelName(), createMessageResponse.getMessage());
    }

//    private CreateMessageRequest convert(CreateMessageRestRequestDto requestDto) {
//        return new CreateMessageRequest(requestDto.getChannelName(), requestDto.getMessage());
//    }

    Function<CreateMessageRestRequestDto, CreateMessageRequest> f = 
            requestDto -> new CreateMessageRequest(requestDto.getChannelName(), requestDto.getMessage());

}
