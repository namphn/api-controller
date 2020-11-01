package web.api.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.model.response.Error;
import web.api.model.response.ResponseBase;
import web.api.rpc.user.LoginResponse;
import web.api.service.GrpcClientUserService;
import web.api.model.request.LoginRequest;
import web.api.model.response.Status;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class LoginController {

    private final GrpcClientUserService grpcClientUserService;

    public LoginController(GrpcClientUserService grpcClientUserService) {
        this.grpcClientUserService = grpcClientUserService;
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = null;
        ResponseBase responseBase = new ResponseBase();
        try{
            response =  grpcClientUserService.login(loginRequest);
        } catch (Exception e) {
            responseBase.setStatusCode(Error.INTERNAL_SERVER.errorCode);
            responseBase.setStatus(Status.INTERNAL_SERVER);
            responseBase.setData(Error.INTERNAL_SERVER);
            return new ResponseEntity(responseBase, HttpStatus.OK);
        }
        if(response != null && response.getStatus().equals(Status.ACCEPT)) {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(Status.ACCEPT);
            responseBase.setData(new web.api.model.response.LoginResponse(response));
        }
        else if(response != null) {
            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(response.getStatus());
        }

        return new ResponseEntity(responseBase, HttpStatus.OK);
    }
}
