package mk.ukim.finki.recipecatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.recipecatalog.domain.models.RecipeId;
import mk.ukim.finki.recipecatalog.services.RecipeService;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.events.recipes.FavoriteRecipeAddedEvent;
import mk.ukim.finki.sharedkernel.domain.events.recipes.FavoriteRecipeRemovedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeEventListener implements RabbitListenerConfigurer {

    private final RecipeService recipeService;

    @RabbitListener(queues = TopicHolder.QUEUE_FAVORITE_RECIPE_ADDED)
    public void consumeFavoriteRecipeAddedEvent(FavoriteRecipeAddedEvent event) {
        recipeService.increaseLikes(RecipeId.of(event.getRecipeId()));
    }

    @RabbitListener(queues = TopicHolder.QUEUE_FAVORITE_RECIPE_REMOVED)
    public void consumeFavoriteRecipeRemovedEvent(FavoriteRecipeRemovedEvent event) {
            recipeService.decreaseLikes(RecipeId.of(event.getRecipeId()));
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
