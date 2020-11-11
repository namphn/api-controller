package web.api.controller.newsfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import web.api.rpc.newsfeed.NewsFeedService;
import web.api.rpc.newsfeed.Post;
import web.api.service.GrpcClientNewsFeedService;

import java.util.List;

@Controller
public class NewsFeedSocketController {

    @Autowired
    private GrpcClientNewsFeedService grpcClientNewsFeedService;

    @MessageMapping("/newsfeed/{userId}")
    public List<Post> listenNews(@Payload Post newPost) {
        List<Post> allNewPost =
    }

}
