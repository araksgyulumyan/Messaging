package com.messaging.gateway.client.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messaging.gateway.client.slack.request.SendMessageClientRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SlackMessagingServiceClient {

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${messaging.service.slack.url}")
    private String slackMessagingServiceUrl;

    public void sendMessage(final SendMessageClientRequest request) {
        final HttpPost httpPost = new HttpPost(slackMessagingServiceUrl);
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpPost.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        try {
            final String json = objectMapper.writeValueAsString(request);
            httpPost.setEntity(new StringEntity(json));
            EntityUtils.consume(httpClient.execute(httpPost).getEntity());
        } catch (IOException exception) {
            throw new RuntimeException("Failed to execute send message request", exception);
        }
    }

}
