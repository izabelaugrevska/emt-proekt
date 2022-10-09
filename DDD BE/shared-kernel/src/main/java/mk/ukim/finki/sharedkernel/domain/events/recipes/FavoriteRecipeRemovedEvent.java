package mk.ukim.finki.sharedkernel.domain.events.recipes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteRecipeRemovedEvent extends DomainEvent implements Serializable {

    private String recipeId;

    public FavoriteRecipeRemovedEvent(String recipeId) {
        super(TopicHolder.TOPIC_FAVORITE_RECIPE_REMOVED, TopicHolder.QUEUE_FAVORITE_RECIPE_REMOVED);
        this.recipeId = recipeId;
    }

}
