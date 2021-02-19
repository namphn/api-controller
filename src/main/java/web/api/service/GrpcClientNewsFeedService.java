package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;
import web.api.model.request.LikeRequest;
import web.api.model.request.PostRequest;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.rpc.follow.GetFollowerAndFollowingRequest;
import web.api.rpc.follow.GetFollowingResponse;
import web.api.rpc.newsfeed.*;
import web.api.rpc.user.GetUserAvatarResponse;
import web.api.rpc.user.GetUserNameRequest;
import web.api.rpc.user.GetUserNameResponse;
import web.api.rpc.user.UserServiceGrpc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrpcClientNewsFeedService {
    @Value( "${path.images}")
    private String imageStore;
    private final static String IMAGE_SOURCE = "images/";
    private final static String AVATAR_SOURCE = "avatars/";
    @Autowired
    private MessageSendingOperations<String> messagingTemplate;

    @Autowired
    private FileService fileService;

    @Autowired
    @Qualifier("newsfeed-service")
    private ManagedChannel newsFeedChannel;

    @Autowired
    @Qualifier("user-service")
    private ManagedChannel userChannel;

    @Autowired
    private GrpcClientUserService grpcClientUserService;

    @Autowired
    private GrpcClientFollowService grpcClientFollowService;

    public ResponseBase getNewsFeed(String userId){
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        GetNewsFeedRequest.Builder request = GetNewsFeedRequest.newBuilder();
        ResponseBase responseBase = new ResponseBase();
        request.setUserId(userId);
        GetNewsFeedResponse response = null;
        try {
            response = stub.getNewsFeed(request.build());
        } catch (Exception e) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.ERROR);
        }

        if(response == null) {
            responseBase.setStatus(Status.NODATA);
            responseBase.setStatusCode(Status.StatusCode.NODATA);
        }
        else {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(Status.SUCCESS);
            List<web.api.model.newsfeed.Post> post = new ArrayList<web.api.model.newsfeed.Post>();

            response.getPostsList().forEach(e -> {
                web.api.model.newsfeed.Post postResponse = new web.api.model.newsfeed.Post();
                postResponse.setId(e.getId());
                postResponse.setUserAvatar(e.getUserAvatar());
                postResponse.setUserId(e.getUserId());
                postResponse.setContent(e.getContent());
                postResponse.setImage(IMAGE_SOURCE + e.getImages());
                postResponse.setUserName(e.getUserName());

                if(e.getUserAvatar() != null)

                if(!e.getPostTime().isEmpty()) postResponse.setPostTime(LocalDateTime.parse(e.getPostTime()));

                List<web.api.model.newsfeed.Comment> comments = new ArrayList<>();
                e.getCommentsList().forEach(comment -> {
                    web.api.model.newsfeed.Comment userComment = new web.api.model.newsfeed.Comment();
                    userComment.setUserId(e.getUserId());
                    userComment.setCommentContent(e.getContent());

                    List<web.api.model.newsfeed.Comment> childComments = new ArrayList<>();
                    e.getCommentsList().forEach(childComment -> {
                        web.api.model.newsfeed.Comment resChildComment = new web.api.model.newsfeed.Comment();
                        resChildComment.setUserId(childComment.getUserId());
                        resChildComment.setCommentContent(childComment.getContent());
                        resChildComment.setUserAvatar(childComment.getUserAvatar());
                        resChildComment.setCommentTime(LocalDateTime.parse(childComment.getTimeComment()));

                        childComments.add(resChildComment);
                    });

                    userComment.setChildComment(childComments);
                });
                postResponse.setComment(comments);
                postResponse.setLikes(e.getLikesList() == null ? new ArrayList<>() : e.getLikesList());

                List<web.api.model.newsfeed.Share> shares = new ArrayList<>();
                if(e.getSharesList() != null) {
                    e.getSharesList().forEach(share -> {
                        web.api.model.newsfeed.Share shareDto = new web.api.model.newsfeed.Share();
                        shareDto.setUserId(share.getUserId());
                        shareDto.setShareContent(share.getContent());

                        shares.add(shareDto);
                    });
                }
                postResponse.setShare(shares);
                post.add(postResponse);

            });

            responseBase.setData(post);
        }

        return responseBase;
    }

    public SaveNewPostResponse saveNewPost(Post post){
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        SaveNewPostResponse response = stub.saveNewPost(post);
        return response;
    }

    public LikeResponse like(LikeRequest request){
        web.api.rpc.newsfeed.LikeRequest.Builder grpcRequest =
                web.api.rpc.newsfeed.LikeRequest.newBuilder();
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setUserId(request.getUserId());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        LikeResponse grpcResponse = stub.like(grpcRequest.build());
        return grpcResponse;
    }

    public ShareResponse share(web.api.model.request.ShareRequest request){
        ShareRequest.Builder grpcRequest = ShareRequest.newBuilder();
        grpcRequest.setUserId(request.getUserId());
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setContent(request.getContent());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub
                = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        ShareResponse response = stub.share(grpcRequest.build());
        return response;
    }

    public TagResponse tag(web.api.model.request.TagRequest request){
        TagRequest.Builder grpcRequest = TagRequest.newBuilder();
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setUserId(request.getUserId());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        TagResponse response = stub.tag(grpcRequest.build());
        return response;
    }

    /**
     *
     * @param request
     * @return response
     */

    public CommentResponse comment(web.api.model.request.CommentRequest request){
        CommentRequest.Builder grpcRequest = CommentRequest.newBuilder();
        grpcRequest.setContent(request.getContent());
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setUserId(request.getUserId());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub
                = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        CommentResponse response = stub.comment(grpcRequest.build());
        return response;
    }

    /**
     *
     * @param postRequest
     * @return responseBase
     */
    public ResponseBase postNewPost(PostRequest postRequest) {
        Post.Builder rpcPost = Post.newBuilder();
        rpcPost.setContent(postRequest.getContent());
        rpcPost.setUserId(postRequest.getUserId());
        String path = fileService.saveFile(postRequest.getImage(), imageStore, postRequest.getUserId());
        ResponseBase responseBase = new ResponseBase();
        GetUserNameRequest.Builder getUserAvatarRpcRequest = GetUserNameRequest.newBuilder();
        getUserAvatarRpcRequest.setUserId(postRequest.getUserId());

        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        UserServiceGrpc.UserServiceBlockingStub userStub = UserServiceGrpc.newBlockingStub(userChannel);

        if(path != null) {
            rpcPost.setImages(path);
        }

        SaveNewPostResponse response = null;
        GetUserNameResponse getUserNameResponse = null;
        try {
            response = stub.saveNewPost(rpcPost.build());
            getUserNameResponse = userStub.getUserName(getUserAvatarRpcRequest.build());

            if(response != null && getUserNameResponse != null) {
                responseBase.setStatusCode(Status.StatusCode.NORMAL);
                responseBase.setStatus(response.getStatus());
                web.api.model.newsfeed.Post newPost = new web.api.model.newsfeed.Post();
                newPost.setUserId(postRequest.getUserId());
                newPost.setImage(IMAGE_SOURCE + path);
                newPost.setComment(new ArrayList<>());
                newPost.setContent(postRequest.getContent());
                newPost.setUserName(getUserNameResponse.getUserName());

                String userAvatar = grpcClientUserService.getUserAvatar(postRequest.getUserId());
                newPost.setUserAvatar(userAvatar);
                responseBase.setData(newPost);
                sendPostToUser(newPost);

                GetFollowerAndFollowingRequest.Builder getFollowerRequest = GetFollowerAndFollowingRequest.newBuilder();
                getFollowerRequest.setUserId(postRequest.getUserId());
                GetFollowingResponse getFollowingResponse = null;

//                try {
//                    getFollowingResponse = grpcClientFollowService.getAllFollower(postRequest.getUserId(), );
//                }
            }
            else {
                responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
                responseBase.setStatus(Status.ERROR);
            }
        } catch (Exception e) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.ERROR);
        }

        return responseBase;
    }

    public void sendPostToUser(web.api.model.newsfeed.Post newPost) {
        this.messagingTemplate.convertAndSend("/topic/public", newPost);
    }
}
