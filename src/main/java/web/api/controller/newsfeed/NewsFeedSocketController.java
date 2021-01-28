package web.api.controller.newsfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import web.api.model.request.PostRequest;
import web.api.service.GrpcClientNewsFeedService;

@Controller
public class NewsFeedSocketController {

    @Autowired
    private GrpcClientNewsFeedService grpcClientNewsFeedService;

//    @SendTo("news/{groupId}")
//    public List<Post> listenNews(@Payload Post newPost, @DestinationVariable String groupId) {
//        List<Post> allNewPost = grpcClientNewsFeedService.listenNewsFeesChange(newPost);
//    }

    @MessageMapping("app/newsfeed")
    @SendTo("topic/public")
    public PostRequest postNews(@Payload PostRequest newPost) {
        grpcClientNewsFeedService.postNewPost(newPost);
        return newPost;
    }
}
