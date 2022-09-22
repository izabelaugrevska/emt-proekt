package mk.ukim.finki.sharedkernel.domain.events.recipes;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

@Getter
public class FavoriteRecipeRemovedEvent extends DomainEvent {

    private String recipeId;

    public FavoriteRecipeRemovedEvent(String recipeId) {
        super(TopicHolder.TOPIC_FAVORITE_RECIPE_REMOVED, TopicHolder.QUEUE_FAVORITE_RECIPE_REMOVED);
        this.recipeId = recipeId;
    }

}
