package web.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.service.user.model.request.NewPasswordRequest;
import web.service.user.model.request.PasswordForgotRequest;
import web.service.user.model.response.NewPasswordResponse;
import web.service.user.model.response.PasswordForgotResponse;
import web.service.user.model.response.Status;
import web.api.service.GrpcClientService;
import web.service.user.service.PasswordForgotTokenService;
import web.service.user.service.SendingMailService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class PasswordForgotController {

    private final GrpcClientService grpcClientService;

    public PasswordForgotController(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity sendPasswordReset(@RequestBody @Valid PasswordForgotRequest passwordForgotRequest){
        PasswordForgotResponse response = grpcClientService.forgotPassword(passwordForgotRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/verifying-reset-password")
    public ResponseEntity setNewPassword(@RequestParam("token") String token, @RequestBody NewPasswordRequest request){
        System.out.println(token);
        NewPasswordResponse response = grpcClientService.setNewPassword(request, token);
        if(response.getStatus() == Status.SAVED_NEW_PASSWORD){
            return new ResponseEntity(response, HttpStatus.OK);
        } else return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
