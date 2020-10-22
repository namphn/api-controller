package web.api.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.api.model.response.Status;
import web.api.model.response.VerificationEmailResponse;
import web.api.model.response.VerificationResetPasswordResponse;
import web.api.service.GrpcClientUserService;

@Controller
public class VerificationController {

    private final GrpcClientUserService grpcClientService;

    public VerificationController(GrpcClientUserService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }


    @GetMapping("/verifying-email")
    public String verifyEmail(@RequestParam("token") String token) {
        VerificationEmailResponse response = grpcClientService.verifyingEmail(token);
        if(response.getStatus().equals(Status.SUCCESSFULLY_VERIFY)){
            return "verify_email.html";
        }
        return "sad";
    }

    @GetMapping("/verifying-reset-password")
    public VerificationResetPasswordResponse verifyResetPasswordToken(@RequestParam("token") String token){
        return grpcClientService.verifyingResetPasswordToken(token);
    }
}
