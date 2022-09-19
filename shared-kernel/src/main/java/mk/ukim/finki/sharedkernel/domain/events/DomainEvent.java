package mk.ukim.finki.sharedkernel.domain.events;

import lombok.Getter;

import java.time.Instant;

@Getter
public class DomainEvent {

    private String topic;
    private Instant occurredOn;

    public DomainEvent(String topic) {
        this.occurredOn = Instant.now();
        this.topic = topic;
    }

}
