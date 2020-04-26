package web.api.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.api.model.request.RegistrationInformationRequest;
import web.api.model.request.RegistrationRequest;
import web.api.model.response.Error;
import web.api.model.response.RegistrationResponse;
import web.api.model.response.Status;
import web.api.service.GrpcClientUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class RegistrationNewAccountController {


    private final GrpcClientUserService grpcClientService;

    public RegistrationNewAccountController(GrpcClientUserService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegistrationRequest request){
        RegistrationResponse response = grpcClientService.registerNewAccount(request);
        if(response.getStatus() == Status.EMAIL_ALREADY_EXISTS) {
            return new ResponseEntity(Error.HAVE_EXIST_ACCOUNT,HttpStatus.BAD_REQUEST);
        } else if(response.getStatus() == Status.INVALID_EMAIL){
            return new ResponseEntity(Error.INVALID_EMAIL,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/register-information")
    public ResponseEntity registerInformationAccount(@Valid @RequestBody RegistrationInformationRequest request){
        String response = grpcClientService.registerInformation(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
