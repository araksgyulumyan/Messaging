package com.messaging.gateway.api.message.controller;

import com.messaging.gateway.api.message.dto.request.CreateMessageRestRequestDto;
import com.messaging.gateway.api.message.dto.response.CreateMessageRestResponseDto;
import com.messaging.gateway.api.message.facade.MessageApiFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageApiFacade messageApiFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateMessageRestResponseDto> createMessage(@Valid @RequestBody final CreateMessageRestRequestDto createMessageRestRequestDto) {
        CreateMessageRestResponseDto responseDto = messageApiFacade.createMessage(createMessageRestRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
