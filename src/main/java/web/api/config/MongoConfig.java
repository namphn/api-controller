package web.api.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {


    @Override
    public MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://web-server:server123@cluster0.tmzfq.mongodb.net/user-service?retryWrites=true&w=majority");
        return new MongoClient(uri);
    }

    @Override
    protected String getDatabaseName() {
        return "web-server";
    }

    @Override
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(),"user-service");
    }

}
