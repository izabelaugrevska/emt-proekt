package mk.ukim.finki.sharedkernel.domain.events.recipes;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

@Getter
public class FavoriteRecipeAddedEvent extends DomainEvent {

    private String recipeId;

    public FavoriteRecipeAddedEvent(String recipeId) {
        super(TopicHolder.TOPIC_FAVORITE_RECIPE_ADDED);
        this.recipeId = recipeId;
    }

}