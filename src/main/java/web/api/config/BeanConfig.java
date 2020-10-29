package web.api.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("newsfeed-service")
    ManagedChannel newsFeedGrpcBeanChanel(){
        return ManagedChannelBuilder.forAddress("localhost", 6569).usePlaintext().build();
    }

    @Bean("user-service")
    ManagedChannel userGrpcBeanChanel(){
        return ManagedChannelBuilder.forAddress("localhost", 6568).usePlaintext().build();
    }

    @Bean("chat-service")
    ManagedChannel chatGrpcBeanChanel(){
        return ManagedChannelBuilder.forAddress("localhost", 6567).usePlaintext().build();
    }

    @Bean("follow-service")
    ManagedChannel followGrpcBeanChanel(){
        return ManagedChannelBuilder.forAddress("localhost", 6569).usePlaintext().build();
    }
}
