package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.api.model.chat.ChatMessage;
import web.api.model.request.GetChanelRequest;
import web.api.model.response.GetChanelResponse;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.rpc.chat.ChatServiceGrpc;
import web.api.rpc.chat.GetChannelResponse;
import web.api.rpc.chat.SubmitResponse;

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

    public ResponseBase submitMessage(ChatMessage message) {
        ResponseBase responseBase = new ResponseBase();
        ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);
        web.api.rpc.chat.ChatMessage chatMessage = convert.convertToChatMessageGrpc(message);
        try {
            SubmitResponse response = stub.submit(chatMessage);
        } catch (Exception e) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatusCode(Status.INTERNAL_SERVER);
        }

        responseBase.setStatusCode(Status.StatusCode.NORMAL);
        responseBase.setStatusCode(Status.SUCCESS);

        return responseBase;
    }
}
