package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.api.model.request.LikeRequest;
import web.api.rpc.newsfeed.*;

@Service
public class GrpcClientNewsFeedService {

    @Autowired
    @Qualifier("news-feed")
    private  ManagedChannel channel;

    public GetNewsFeedResponse getNewsFeed(){
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(channel);
        GetNewsFeedRequest.Builder request = GetNewsFeedRequest.newBuilder();
        GetNewsFeedResponse response = stub.getNewsFeed(request.build());
        return response;
    }

    public SaveNewPostResponse saveNewPost(Post post){
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(channel);
        SaveNewPostResponse response = stub.saveNewPost(post);
        return response;
    }

    public LikeResponse like(LikeRequest request){
        web.api.rpc.newsfeed.LikeRequest.Builder grpcRequest =
                web.api.rpc.newsfeed.LikeRequest.newBuilder();
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setUserId(request.getUserId());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(channel);
        LikeResponse grpcResponse = stub.like(grpcRequest.build());
        return grpcResponse;
    }

    public ShareResponse share(web.api.model.request.ShareRequest request){
        ShareRequest.Builder grpcRequest = ShareRequest.newBuilder();
        grpcRequest.setUserId(request.getUserId());
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setContent(request.getContent());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub
                = NewsFeedServiceGrpc.newBlockingStub(channel);
        ShareResponse response = stub.share(grpcRequest.build());
        return response;
    }

    public TagResponse tag(web.api.model.request.TagRequest request){
        TagRequest.Builder grpcRequest = TagRequest.newBuilder();
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setUserId(request.getUserId());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(channel);
        TagResponse response = stub.tag(grpcRequest.build());
        return response;
    }

    public CommentResponse comment(web.api.model.request.CommentRequest request){
        CommentRequest.Builder grpcRequest = CommentRequest.newBuilder();
        grpcRequest.setContent(request.getContent());
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setUserId(request.getUserId());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub
                = NewsFeedServiceGrpc.newBlockingStub(channel);
        CommentResponse response = stub.comment(grpcRequest.build());
        return response;
    }
}
