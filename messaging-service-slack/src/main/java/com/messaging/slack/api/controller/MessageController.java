package com.messaging.slack.api.controller;

import com.messaging.slack.api.dto.request.SendMessageRestRequestDto;
import com.messaging.slack.api.dto.response.GetMessageRestResponseDto;
import com.messaging.slack.client.SlackClient;
import com.messaging.slack.client.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
public class MessageController {

    private final SlackClient slackClient;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetMessageRestResponseDto> sendMessage(@Valid @RequestBody final SendMessageRestRequestDto sendMessageRestRequestDto) {
        log.debug("Processing send message request - {}", sendMessageRestRequestDto);
        slackClient.sendMessage(convert(sendMessageRestRequestDto));
        return new ResponseEntity<>(convertResponse(sendMessageRestRequestDto), HttpStatus.CREATED);
    }

    private GetMessageRestResponseDto convertResponse(final SendMessageRestRequestDto messageDto) {
        return new GetMessageRestResponseDto(messageDto.getChannelName(), messageDto.getMessage());
    }

    private SendMessageRequest convert(final SendMessageRestRequestDto messageDto) {
        return new SendMessageRequest(messageDto.getChannelName(), messageDto.getMessage());
    }
}
