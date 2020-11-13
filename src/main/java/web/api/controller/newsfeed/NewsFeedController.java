package web.api.controller.newsfeed;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.model.request.*;
import web.api.model.response.ResponseBase;
import web.api.service.GrpcClientNewsFeedService;

@RestController
@RequestMapping("/posts")
public class NewsFeedController {

    private final GrpcClientNewsFeedService grpcClientNewsFeedService;
    private final Gson gson;
    private final NewsFeedSocketController socketController;

    public NewsFeedController(GrpcClientNewsFeedService grpcClientNewsFeedService, Gson gson, NewsFeedSocketController socketController) {
        this.grpcClientNewsFeedService = grpcClientNewsFeedService;
        this.gson = gson;
        this.socketController = socketController;
    }

    @GetMapping("/{userId}")
    public ResponseEntity getNewsFeed(@PathVariable String userId){
        ResponseBase response = grpcClientNewsFeedService.getNewsFeed(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity newPost(@ModelAttribute PostRequest postRequest){
        ResponseBase responseBase = grpcClientNewsFeedService.postNewPost(postRequest);
        return new ResponseEntity(responseBase, HttpStatus.OK);
    }
//
//    @PostMapping("/like")
//    public ResponseEntity likePost(@RequestBody LikeRequest request){
//        LikeResponse response = grpcClientNewsFeedService.like(request);
//        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
//    }
//    @PostMapping("/share")
//    public ResponseEntity sharePost(@RequestBody ShareRequest request){
//        ShareResponse response = grpcClientNewsFeedService.share(request);
//        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
//    }
//
//    @PostMapping("/tag")
//    public ResponseEntity tag(@RequestBody TagRequest request){
//        TagResponse response = grpcClientNewsFeedService.tag(request);
//        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
//    }
//
//    @PostMapping("/comment")
//    public ResponseEntity comment(@RequestBody CommentRequest request){
//        CommentResponse response = grpcClientNewsFeedService.comment(request);
//        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
//    }
}
