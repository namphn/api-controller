package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import web.api.model.request.LikeRequest;
import web.api.model.request.PostRequest;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.rpc.follow.GetFollowerAndFollowingRequest;
import web.api.rpc.follow.GetFollowingResponse;
import web.api.rpc.newsfeed.*;
import web.api.rpc.user.UserServiceGrpc;

@Service
public class GrpcClientNewsFeedService {
    @Value( "${path.images}")
    private String imageStore;

    @Autowired
    private FileService fileService;

    @Autowired
    @Qualifier("newsfeed-service")
    private ManagedChannel newsFeedChannel;

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
            responseBase.setData(response.getPostsList());
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

        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(newsFeedChannel);
        UserServiceGrpc.UserServiceBlockingStub userStub = UserServiceGrpc.newBlockingStub(newsFeedChannel);

        if(path != null) {
            rpcPost.setImages(path);
        }

        SaveNewPostResponse response = null;
        try {
            response = stub.saveNewPost(rpcPost.build());

            if(response != null) {
                responseBase.setStatusCode(Status.StatusCode.NORMAL);
                responseBase.setStatus(response.getStatus());
                web.api.model.newsfeed.Post newPost = new web.api.model.newsfeed.Post();
                newPost.setUserId(postRequest.getUserId());
                newPost.setUserAvatar(path);

                String userAvatar = grpcClientUserService.getUserAvatar(postRequest.getUserId());
                newPost.setUserAvatar(userAvatar);
                responseBase.setData(newPost);

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
}
