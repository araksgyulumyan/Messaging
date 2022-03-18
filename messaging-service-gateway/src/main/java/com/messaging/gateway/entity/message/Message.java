package com.messaging.gateway.entity.message;

import com.messaging.gateway.entity.channel.Channel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Message should not be null")
    private String message;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false, updatable = false)
    private Channel channel;
}
