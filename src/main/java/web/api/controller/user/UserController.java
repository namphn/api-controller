package web.api.controller.user;

import com.google.gson.Gson;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.model.request.ChangeUsernameRequest;
import web.api.service.GrpcClientUserService;
import web.api.rpc.user.GetAllUserRequest;
import web.api.rpc.user.GetAllUserResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GrpcClientUserService grpcClientService;
    private final Gson gson;

    public UserController(GrpcClientUserService grpcClientService, Gson gson) {
        this.grpcClientService = grpcClientService;
        this.gson = gson;
    }

    @GetMapping()
    public ResponseEntity getUsers(@Param("page") int page) {
        GetAllUserRequest.Builder request = GetAllUserRequest.newBuilder();
        request.setPage(page);
        GetAllUserResponse response = null;
        try {
            response =  grpcClientService.getUsers(request.build());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(gson.toJson(response), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity changeUsername(@PathVariable String userId, @RequestBody ChangeUsernameRequest request) {
        return grpcClientService.changeUserName(userId, request);
    }
}
