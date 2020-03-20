package web.api.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.model.request.NewPasswordRequest;
import web.api.model.request.PasswordForgotRequest;
import web.api.model.response.NewPasswordResponse;
import web.api.model.response.PasswordForgotResponse;
import web.api.model.response.Status;
import web.api.service.GrpcClientUserService;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class PasswordForgotController {

    private final GrpcClientUserService grpcClientService;

    public PasswordForgotController(GrpcClientUserService grpcClientService) {
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
