package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.api.model.response.GetFollowerAndFollowingResponse;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.rpc.follow.*;

@Service
public class GrpcClientFollowService {

    @Autowired
    private ConvertToGrpcRequest convert;

    @Autowired
    @Qualifier("follow-service")
    private ManagedChannel channel;

    public ResponseBase getAllFollower(String userId, int getFlg) {
        GetFollowerAndFollowingRequest.Builder request = GetFollowerAndFollowingRequest.newBuilder();
        request.setUserId(userId);
        GetFollowerResponse getFollowerResponse = null;
        GetFollowingResponse getFollowingResponse = null;
        GetFollowerAndFollowingResponse getFollowerAndFollowingResponse = new GetFollowerAndFollowingResponse();
        ResponseBase responseBase = new ResponseBase();
        FollowRpcServiceGrpc.FollowRpcServiceBlockingStub stub =FollowRpcServiceGrpc.newBlockingStub(channel);

        try {
            switch (getFlg) {
                case 0:
                    getFollowerResponse = stub.getFollower(request.build());
                    getFollowingResponse = stub.getFollowing(request.build());
                    break;

                case 1:
                    getFollowerResponse = stub.getFollower(request.build());
                    break;

                case 2:
                    getFollowingResponse = stub.getFollowing(request.build());
                    break;
            }
        } catch (Exception e) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.INTERNAL_SERVER);
            return responseBase;
        }

        if(getFollowerResponse != null) getFollowerAndFollowingResponse.setFollowers(getFollowerResponse.getFollowersList());
        if(getFollowingResponse != null) getFollowerAndFollowingResponse.setFollowings(getFollowingResponse.getFollowingList());

        if(getFollowerAndFollowingResponse.getFollowers() != null
            || getFollowerAndFollowingResponse.getFollowings() != null)
        {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(Status.SUCCESS);
            responseBase.setData(getFollowerAndFollowingResponse);
        }
        else
        {
            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(Status.NODATA);
        }

        return responseBase;
    }

    public ResponseBase addFollow(String userId, String userAdd, boolean addFollower) {
        AddFollowRequest.Builder request = AddFollowRequest.newBuilder();
        request.setUserId(userId);
        request.setUserAdd(userAdd);
        request.setAddFollower(addFollower);
        FollowRpcServiceGrpc.FollowRpcServiceBlockingStub stub =FollowRpcServiceGrpc.newBlockingStub(channel);

        ResponseBase responseBase = new ResponseBase();

        AddFollowResponse response = null;
        try
        {
            response = stub.addFollow(request.build());
        } catch (Exception e)
        {
            responseBase.setStatus(Status.INTERNAL_SERVER);
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            return responseBase;
        }
        if(response == null) {
            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(Status.SUCCESS);
        }
        else {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(response.getStatus());
        }

        return responseBase;
    }
}
