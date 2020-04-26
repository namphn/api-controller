package web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableMongoRepositories("web.api.repository")
public class ApiControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiControllerApplication.class, args);
    }
}
