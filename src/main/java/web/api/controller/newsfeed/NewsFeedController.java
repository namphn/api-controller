package web.api.controller.newsfeed;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.api.model.request.CommentRequest;
import web.api.model.request.LikeRequest;
import web.api.model.request.ShareRequest;
import web.api.model.request.TagRequest;
import web.api.service.GrpcClientNewsFeedService;
import web.api.rpc.newsfeed.*;

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
        postGrpc.addAllLikes(post.getLikes());
        postGrpc.addAllTags(post.getTags());
        postGrpc.addAllShares(new ArrayList<Share>());
        grpcClientNewsFeedService.saveNewPost(postGrpc.build());
        return new ResponseEntity(gson.toJson(post), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity likePost(@RequestBody LikeRequest request){
        LikeResponse response = grpcClientNewsFeedService.like(request);
        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
    }
    @PostMapping("/share")
    public ResponseEntity sharePost(@RequestBody ShareRequest request){
        ShareResponse response = grpcClientNewsFeedService.share(request);
        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
    }

    @PostMapping("/tag")
    public ResponseEntity tag(@RequestBody TagRequest request){
        TagResponse response = grpcClientNewsFeedService.tag(request);
        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity comment(@RequestBody CommentRequest request){
        CommentResponse response = grpcClientNewsFeedService.comment(request);
        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
    }
}
