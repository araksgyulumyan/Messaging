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
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageApiFacade messageApiFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateMessageRestResponseDto> createMessage(@Valid @RequestBody final CreateMessageRestRequestDto createMessageRestRequestDto) {
        var responseDto = messageApiFacade.createMessage(createMessageRestRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    public ResponseEntity<CreateMessageRestResponseDto> example(@Valid @RequestBody final CreateMessageRestRequestDto createMessageRestRequestDto) {
        Function<CreateMessageRestResponseDto, ResponseEntity<CreateMessageRestResponseDto>> mapper =
                x -> new ResponseEntity<>(x, HttpStatus.CREATED);
        return Optional.of(messageApiFacade.createMessage(createMessageRestRequestDto))
                .map(mapper)
                .get();
    }
}
