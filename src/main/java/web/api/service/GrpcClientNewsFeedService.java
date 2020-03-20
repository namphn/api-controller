package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.api.model.request.LikeRequest;
import web.service.grpc.newsfeed.*;

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
        web.service.grpc.newsfeed.LikeRequest.Builder grpcRequest =
                web.service.grpc.newsfeed.LikeRequest.newBuilder();
        grpcRequest.setPostId(request.getPostId());
        grpcRequest.setUserId(request.getUserId());
        NewsFeedServiceGrpc.NewsFeedServiceBlockingStub stub = NewsFeedServiceGrpc.newBlockingStub(channel);
        LikeResponse grpcResponse = stub.like(grpcRequest.build());
        return grpcResponse;
    }
}
