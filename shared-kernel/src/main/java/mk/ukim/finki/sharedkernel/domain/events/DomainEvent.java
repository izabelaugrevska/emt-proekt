package mk.ukim.finki.sharedkernel.domain.events;

import lombok.Getter;

import java.time.Instant;

@Getter
public class DomainEvent {

    private String topic;
    private String queue;
    private Instant occurredOn;

    public DomainEvent(String topic, String queue) {
        this.occurredOn = Instant.now();
        this.topic = topic;
        this.queue = queue;
    }

}
