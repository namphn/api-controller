package web.api.service;

import org.springframework.stereotype.Service;
import web.api.model.request.GetChanelRequest;
import web.api.model.request.PasswordForgotRequest;
import web.api.model.request.RegistrationRequest;
import web.api.rpc.chat.Chat;
import web.api.rpc.chat.ChatMessage;
import web.api.rpc.user.*;

@Service
public class ConvertToGrpcRequest {

    public LoginRequest convertToLoginRequestGprc(web.api.model.request.LoginRequest request){
        LoginRequest.Builder grpcRequest = LoginRequest.newBuilder();
        grpcRequest.setEmail(request.getEmail());
        grpcRequest.setPassword(request.getPassword());
        return grpcRequest.build();
    }


    public RegistrationRequestGrpc convertToRegistrationRequestGrpc(RegistrationRequest request){
        RegistrationRequestGrpc.Builder grpcRequest = RegistrationRequestGrpc.newBuilder();
        grpcRequest.setEmail(request.getEmail());
        grpcRequest.setPassword(request.getPassword());
        grpcRequest.setName(request.getName());
        return grpcRequest.build();
    }

    public PasswordResetRequest convertToPasswordResetRequest(PasswordForgotRequest request){
        PasswordResetRequest.Builder resetRequest = PasswordResetRequest.newBuilder();
        resetRequest.setEmail(request.getEmail());
        return resetRequest.build();
    }

    public NewPasswordRequest convertToNewPasswordRequestGrpc(web.api.model.request.NewPasswordRequest request,
                                                              String token){
        NewPasswordRequest.Builder grpcRequest = NewPasswordRequest.newBuilder();
        grpcRequest.setNewPassword(request.getNewPassword());
        grpcRequest.setNewPasswordConfirm(request.getNewPasswordConfirm());
        grpcRequest.setToken(token);
        return grpcRequest.build();
    }

    public RegistrationInformationRequest convertToRegistrationInformationRequestGrpc(
            web.api.model.request.RegistrationInformationRequest request){
        RegistrationInformationRequest.Builder grpcRequest = RegistrationInformationRequest.newBuilder();
        grpcRequest.setEmail(request.getEmail());
        grpcRequest.setPhone(request.getPhone());
        grpcRequest.setUserName(request.getUserName());
        return grpcRequest.build();
    }

    public VerificationResetPasswordTokenRequest setVerificationPassTokenRequest(String token){
        VerificationResetPasswordTokenRequest.Builder request = VerificationResetPasswordTokenRequest.newBuilder();
        request.setToken(token);
        return request.build();
    }

    public web.api.rpc.chat.GetChannelRequest convertToGetChannelRequestGrpc(GetChanelRequest request) {
        web.api.rpc.chat.GetChannelRequest.Builder grpcRequest = web.api.rpc.chat.GetChannelRequest.newBuilder();
        grpcRequest.setUserId1(request.getUserId1());
        grpcRequest.setUserId2(request.getUserId2());
        return grpcRequest.build();
    }

    public ChatMessage convertToChatMessageGrpc(web.api.model.chat.ChatMessage message) {
        ChatMessage.Builder grpcMessage = ChatMessage.newBuilder();
        grpcMessage.setUserId1(message.getUserId1());
        grpcMessage.setUserId2(message.getUserId2());
        grpcMessage.setContent(message.getContent());
        return grpcMessage.build();
    }
}
