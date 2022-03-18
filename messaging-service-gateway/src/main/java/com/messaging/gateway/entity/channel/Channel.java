package com.messaging.gateway.entity.channel;

import com.messaging.gateway.entity.message.Message;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name should not be null")
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "channel")
    private Set<Message> messages;
}
