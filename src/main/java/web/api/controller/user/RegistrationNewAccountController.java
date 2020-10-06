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

        if(response == null) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.INTERNAL_SERVER);
            return new ResponseEntity(responseBase, HttpStatus.OK);
        }

        if(!response.getStatus().equals(Status.SENT_EMAIL)) {
            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(response.getStatus());
            responseBase.setData(response);
        } else{
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(response.getStatus());
        }
        return new ResponseEntity(responseBase, HttpStatus.OK);
    }

    @PostMapping("/register-information")
    public ResponseEntity registerInformationAccount(@Valid @RequestBody RegistrationInformationRequest request){
        String response = grpcClientService.registerInformation(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
