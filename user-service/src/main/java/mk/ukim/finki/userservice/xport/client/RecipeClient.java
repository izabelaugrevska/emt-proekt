package mk.ukim.finki.userservice.xport.client;

import mk.ukim.finki.userservice.domain.models.UserId;
import mk.ukim.finki.userservice.domain.valueobjects.Recipe;
import mk.ukim.finki.userservice.domain.valueobjects.RecipeId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public RecipeClient(@Value("${recipe-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Recipe> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/recipes").build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Recipe>>() {
                    }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Recipe> findAllCreatedBy(UserId userId) {
        try {
            return restTemplate.exchange(uri().path("/api/recipes/user/" + userId).build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Recipe>>() {
                    }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<Recipe> findById(RecipeId recipeId) {
        try {
            return restTemplate.exchange(uri().path("/api/recipes/" + recipeId.getId()).build().toUri(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Optional<Recipe>>() {
                    }).getBody();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
