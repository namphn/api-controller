package web.api.controller.newsfeed;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.api.model.request.LikeRequest;
import web.api.service.GrpcClientNewsFeedService;
import web.service.grpc.newsfeed.*;

import java.util.ArrayList;

@RestController
public class NewsFeedController {

    private final GrpcClientNewsFeedService grpcClientNewsFeedService;
    private final Gson gson;

    public NewsFeedController(GrpcClientNewsFeedService grpcClientNewsFeedService, Gson gson) {
        this.grpcClientNewsFeedService = grpcClientNewsFeedService;
        this.gson = gson;
    }

    @GetMapping("/news-feed")
    public ResponseEntity getNewsFeed(){
        GetNewsFeedResponse response = grpcClientNewsFeedService.getNewsFeed();
        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity newPost(@RequestBody web.api.model.newsfeed.Post post){
        Post.Builder postGrpc = Post.newBuilder();
        postGrpc.setContent(post.getContent());
        postGrpc.setUserId(post.getUserId());
        Comment.Builder comment = Comment.newBuilder();
        postGrpc.addAllComments(new ArrayList<Comment>());
        postGrpc.addAllLikes(new ArrayList<Like>());
        postGrpc.addAllTags(new ArrayList<Tag>());
        postGrpc.addAllShares(new ArrayList<Share>());
        grpcClientNewsFeedService.saveNewPost(postGrpc.build());
        return new ResponseEntity(gson.toJson(post), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity likePost(@RequestBody LikeRequest request){
        LikeResponse response = grpcClientNewsFeedService.like(request);
        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
    }
}
