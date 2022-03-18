package com.messaging.slack.client;

import com.messaging.slack.client.model.SendMessageRequest;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SlackClient {

    @Value("${slack.application.api.token}")
    private String apiToken;

    public void sendMessage(final SendMessageRequest sendMessageRequest) {
        final MethodsClient client = Slack.getInstance().methods();
        try {
            final ChatPostMessageResponse response = client.chatPostMessage(r -> r
                    .token(apiToken)
                    .channel(sendMessageRequest.getChannelName())
                    .text(sendMessageRequest.getMessage())
            );
            log.info("Response {}", response);
        } catch (IOException | SlackApiException e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}
