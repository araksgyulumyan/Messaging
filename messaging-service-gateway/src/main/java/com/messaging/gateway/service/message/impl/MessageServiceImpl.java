package com.messaging.gateway.service.message.impl;

import com.messaging.gateway.entity.channel.Channel;
import com.messaging.gateway.entity.message.Message;
import com.messaging.gateway.repository.channel.ChannelRepository;
import com.messaging.gateway.repository.message.MessageRepository;
import com.messaging.gateway.service.message.MessageService;
import com.messaging.gateway.service.message.model.request.CreateMessageRequest;
import com.messaging.gateway.service.message.model.response.CreateMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public CreateMessageResponse createMessageForChannel(final CreateMessageRequest request) {
        assertCreateMessageRequest(request);
        createChannelIfNotExist(request.getChannelName());
        final Channel channel = channelRepository.findByName(request.getChannelName()).get();
        Message message = new Message();
        message.setChannel(channel);
        message.setMessage(request.getMessage());
        message = messageRepository.save(message);
        return new CreateMessageResponse(request.getChannelName(), message.getMessage());
    }

    private void createChannelIfNotExist(final String channelName) {
        final Optional<Channel> channelOptional = channelRepository.findByName(channelName);
        if (!channelOptional.isPresent()) {
            channelRepository.save(Channel.builder()
                    .withName(channelName)
                    .build());
        }
    }

    private void assertCreateMessageRequest(CreateMessageRequest request) {
        Assert.notNull(request, "Create message request should not be null");
        Assert.hasText(request.getChannelName(), "Channel name should not be null");
        Assert.hasText(request.getMessage(), "Message should not be null");
    }
}
