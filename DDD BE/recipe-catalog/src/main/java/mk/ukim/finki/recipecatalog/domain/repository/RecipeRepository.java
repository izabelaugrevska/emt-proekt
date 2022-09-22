package mk.ukim.finki.recipecatalog.domain.repository;

import mk.ukim.finki.recipecatalog.domain.valueobjects.UserId;
import mk.ukim.finki.recipecatalog.domain.models.Recipe;
import mk.ukim.finki.recipecatalog.domain.models.RecipeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, RecipeId> {

    List<Recipe> findAllByCreatorIdEquals(UserId creatorId);

}
