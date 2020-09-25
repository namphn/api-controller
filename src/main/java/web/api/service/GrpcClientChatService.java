package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.api.model.request.GetChanelRequest;
import web.api.model.response.GetChanelResponse;
import web.api.rpc.chat.ChatServiceGrpc;
import web.api.rpc.chat.GetChannelResponse;

@Service
public class GrpcClientChatService {
    @Autowired
    @Qualifier("chat-service")
    private ManagedChannel channel;
    @Autowired
    private  ConvertToGrpcRequest convert;

    public GetChanelResponse getChannel(GetChanelRequest request) {
        ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);
        web.api.rpc.chat.GetChannelRequest grpcRequest = convert.convertToGetChannelRequestGrpc(request);
        GetChannelResponse response = stub.getChannel(grpcRequest);
        return new GetChanelResponse(response.getUuid());
    }
}
