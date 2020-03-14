package web.api.service;

import org.springframework.stereotype.Service;
import web.service.grpc.*;
import web.service.user.model.request.PasswordForgotRequest;
import web.service.user.model.request.RegistrationRequest;

@Service
public class ConvertToGrpcRequest {

    public LoginRequest convertToLoginRequestGprc(web.service.user.model.request.LoginRequest request){
        LoginRequest.Builder grpcRequest = LoginRequest.newBuilder();
        grpcRequest.setEmail(request.getEmail());
        grpcRequest.setPassword(request.getPassword());
        return grpcRequest.build();
    }


    public RegistrationRequestGrpc convertToRegistrationRequestGrpc(RegistrationRequest request){
        RegistrationRequestGrpc.Builder grpcRequest = RegistrationRequestGrpc.newBuilder();
        grpcRequest.setEmail(request.getEmail());
        grpcRequest.setPassword(request.getPassword());
        return grpcRequest.build();
    }

    public PasswordResetRequest convertToPasswordResetRequest(PasswordForgotRequest request){
        PasswordResetRequest.Builder resetRequest = PasswordResetRequest.newBuilder();
        resetRequest.setEmail(request.getEmail());
        return resetRequest.build();
    }

    public NewPasswordRequest convertToNewPasswordRequestGrpc(web.service.user.model.request.NewPasswordRequest request,
                                                              String token){
        NewPasswordRequest.Builder grpcRequest = NewPasswordRequest.newBuilder();
        grpcRequest.setNewPassword(request.getNewPassword());
        grpcRequest.setNewPasswordConfirm(request.getNewPasswordConfirm());
        grpcRequest.setToken(token);
        return grpcRequest.build();
    }

    public RegistrationInformationRequest convertToRegistrationInformationRequestGrpc(
            web.service.user.model.request.RegistrationInformationRequest request){
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
}
