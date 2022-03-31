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

import java.util.function.Consumer;

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
//        final Optional<Channel> channel = channelRepository.findAll().stream().filter(o -> o.getName() == request.getChannelName()).findFirst();
        final Channel channel = channelRepository.findByName(request.getChannelName()).get();
        Message message = new Message();
        message.setChannel(channel);
        message.setMessage(request.getMessage());
        message = messageRepository.save(message);
        return new CreateMessageResponse(request.getChannelName(), message.getMessage());
    }

    public CreateMessageResponse example(final CreateMessageRequest request) {
        assertCreateMessageRequest(request);
        createChannelIfNotExist(request.getChannelName());
        return channelRepository.findByName(request.getChannelName())
                .map(channel -> {
                    var msg = new Message();
                    ((Consumer<Message>) message -> {
                    })
                            .andThen(message -> message.setChannel(channel))
                            .andThen(message -> message.setMessage(request.getMessage()))
                            .accept(msg);
                    return messageRepository.save(msg);
                })
                .map(message -> new CreateMessageResponse(message.getChannel().getName(), message.getMessage()))
                .get();
    }

    private void createChannelIfNotExist(final String channelName) {
        channelRepository.findByName(channelName)
                .orElse(channelRepository.save(Channel.builder()
                        .withName(channelName)
                        .build()));
    }

    private void assertCreateMessageRequest(CreateMessageRequest request) {
        Assert.notNull(request, "Create message request should not be null");
        Assert.hasText(request.getChannelName(), "Channel name should not be null");
        Assert.hasText(request.getMessage(), "Message should not be null");
    }
}
