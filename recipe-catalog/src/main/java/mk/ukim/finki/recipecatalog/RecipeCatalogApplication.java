package mk.ukim.finki.recipecatalog;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableRabbit
@EntityScan
public class RecipeCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeCatalogApplication.class, args);
    }

}
