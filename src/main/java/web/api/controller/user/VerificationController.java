package web.api.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.model.response.Status;
import web.api.model.response.VerificationEmailResponse;
import web.api.model.response.VerificationResetPasswordResponse;
import web.api.service.GrpcClientUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class VerificationController {

    private final GrpcClientUserService grpcClientService;

    public VerificationController(GrpcClientUserService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @RequestMapping("/ok")
    public String res(){
        return "hello";
    }


    @GetMapping("/verifying-email")
    @ResponseBody
    public ResponseEntity verifyEmail(@RequestParam("token") String token) {
        VerificationEmailResponse response = grpcClientService.verifyingEmail(token);
        if(response.getStatus().equals(Status.SUCCESSFULLY_VERIFY)){
            return new ResponseEntity(response, HttpStatus.OK);
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/verifying-reset-password")
    public VerificationResetPasswordResponse verifyResetPasswordToken(@RequestParam("token") String token){
        return grpcClientService.verifyingResetPasswordToken(token);
    }
}
