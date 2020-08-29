package web.api.controller.user;

import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.service.GrpcClientUserService;
import web.api.model.request.LoginRequest;
import web.api.model.response.LoginResponse;
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
        try{
            response =  grpcClientUserService.login(loginRequest);
        } catch (StatusRuntimeException e) {
            System.out.println(e);
        }
        if(response.getStatus().equals(Status.HAVE_NOT_ACCOUNT)){
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
