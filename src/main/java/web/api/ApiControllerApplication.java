package web.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EnableMongoRepositories("web.api.repository")
public class ApiControllerApplication {
    public static void main(String[] args) {
        run(ApiControllerApplication.class);
    }
}
