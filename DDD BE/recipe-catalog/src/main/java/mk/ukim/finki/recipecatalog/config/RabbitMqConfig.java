package mk.ukim.finki.recipecatalog.config;

import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Bean
    Queue favoriteRecipeAddedQueue() {
        return new Queue(TopicHolder.QUEUE_FAVORITE_RECIPE_ADDED, true);
    }

    @Bean
    Queue favoriteRecipeRemovedQueue() {
        return new Queue(TopicHolder.QUEUE_FAVORITE_RECIPE_REMOVED, true);
    }

    @Bean
    Exchange favoriteRecipeExchange() {
        return ExchangeBuilder.directExchange(TopicHolder.EXCHANGE_FAVORITE_RECIPE).durable(true).build();
    }

    @Bean
    Binding favoriteRecipeAddedBinding() {
        return BindingBuilder
                .bind(favoriteRecipeAddedQueue())
                .to(favoriteRecipeExchange())
                .with(TopicHolder.TOPIC_FAVORITE_RECIPE_ADDED)
                .noargs();
    }

    @Bean
    Binding favoriteRecipeRemovedBinding() {
        return BindingBuilder
                .bind(favoriteRecipeRemovedQueue())
                .to(favoriteRecipeExchange())
                .with(TopicHolder.TOPIC_FAVORITE_RECIPE_REMOVED)
                .noargs();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
