package web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import web.api.config.MongoConfig;

@SpringBootApplication
@EnableMongoRepositories("web.api.repository")
public class ApiControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiControllerApplication.class, args);
    }
}
