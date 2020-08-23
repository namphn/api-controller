package web.api.service;

import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.api.model.request.PasswordForgotRequest;
import web.api.model.request.RegistrationRequest;
import web.api.model.response.PasswordForgotResponse;
import web.api.model.response.RegistrationResponse;
import web.api.model.response.VerificationEmailResponse;
import web.api.model.response.VerificationResetPasswordResponse;
import web.api.rpc.user.*;

@Service
public class GrpcClientUserService {

    @Autowired
    @Qualifier("user-service")
    private ManagedChannel channel;
    @Autowired
    private  ConvertToGrpcRequest convert;

    public web.api.model.response.LoginResponse login(web.api.model.request.LoginRequest loginRequest){
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        web.api.rpc.user.LoginRequest loginRequestGrpc = convert.convertToLoginRequestGprc(loginRequest);
        LoginResponse response = stub.login(loginRequestGrpc);
        return new web.api.model.response.LoginResponse(response);
    }

    public RegistrationResponse registerNewAccount(RegistrationRequest request){
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        RegistrationRequestGrpc requestGrpc = convert.convertToRegistrationRequestGrpc(request);
        RegistrationResponse response = new RegistrationResponse(stub.registration(requestGrpc));
        return response;
    }

    public PasswordForgotResponse forgotPassword(PasswordForgotRequest request){
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        PasswordResetRequest grpcRequest = convert.convertToPasswordResetRequest(request);
        PasswordForgotResponse response = new PasswordForgotResponse(stub.passwordForgot(grpcRequest));
        return response;
    }

    public VerificationEmailResponse verifyingEmail(String token){
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        ConfirmEmailRequest.Builder request = ConfirmEmailRequest.newBuilder();
        request.setToken(token);
        ConfirmEmailResponse response = stub.verificationTokenRegistration(request.build());
        return new VerificationEmailResponse(response);
    }

    public VerificationResetPasswordResponse verifyingResetPasswordToken(String token){
        VerificationResetPasswordTokenRequest request
                = convert.setVerificationPassTokenRequest(token);
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        VerificationResetPasswordTokenResponse grpcResponse = stub.verificationResetPasswordToken(request);
        VerificationResetPasswordResponse response = new VerificationResetPasswordResponse(grpcResponse);
        return response;
    }

    public web.api.model.response.NewPasswordResponse setNewPassword(
            web.api.model.request.NewPasswordRequest request,
            String token){
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        NewPasswordRequest grpcRequest = convert.convertToNewPasswordRequestGrpc(request,token);
        NewPasswordResponse grpcresponse = stub.passwordReset(grpcRequest);
        web.api.model.response.NewPasswordResponse response =
                new web.api.model.response.NewPasswordResponse(grpcresponse);
        return response;
    }

    public String registerInformation(web.api.model.request.RegistrationInformationRequest request){
        RegistrationInformationRequest grpcRequest = convert.convertToRegistrationInformationRequestGrpc(request);
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        RegistrationInformationResponse grpcResponse = stub.registrationInformation(grpcRequest);
        return grpcResponse.getStatus();
    }

    public String getEmailFromToken(GetEmailRequest request){
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        GetEmailResponse response = null;
        try {
            response = stub.getEmailFromToken(request);
        } catch (RuntimeException e){
        }
        return response.getEmail();
    }

    public boolean validateToken(ValidateTokenRequest request){
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        ValidateTokenResponse response = stub.validateToken(request);
        return response.getStatus();
    }

    public GetAllUserResponse getUsers(GetAllUserRequest request) {
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        GetAllUserResponse response = stub.getAllUser(request);
        return response;
    }
}
