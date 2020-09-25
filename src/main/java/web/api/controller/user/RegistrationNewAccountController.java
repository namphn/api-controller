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
import web.api.model.response.ResponseBase;
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
        ResponseBase responseBase = new ResponseBase();
        RegistrationResponse response = null;

        try {
            response = grpcClientService.registerNewAccount(request);
        } catch (Exception e) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.INTERNAL_SERVER);
        }

        if(response.getStatus() == Status.EMAIL_ALREADY_EXISTS) {

            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(Status.EMAIL_ALREADY_EXISTS);
            responseBase.setData(Error.HAVE_EXIST_ACCOUNT);

        } else if(response.getStatus() == Status.INVALID_EMAIL){

            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(Status.INVALID_EMAIL);
            responseBase.setData(Error.INVALID_EMAIL);
        }
        return new ResponseEntity(responseBase, HttpStatus.OK);
    }

    @PostMapping("/register-information")
    public ResponseEntity registerInformationAccount(@Valid @RequestBody RegistrationInformationRequest request){
        String response = grpcClientService.registerInformation(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
