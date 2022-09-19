package mk.ukim.finki.userservice.infra;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(DomainEvent event) {
        rabbitTemplate.convertAndSend(event.getTopic(), event);
    }

}
