package web.api.model.response;

import lombok.Data;
import web.service.grpc.user.RegistrationResponseGrpc;

@Data
public class RegistrationResponse {

    private String email;
    private String password;
    private String status;

    public RegistrationResponse(RegistrationResponseGrpc responseGrpc){
        this.status = responseGrpc.getStatus();
        this.email = responseGrpc.getEmail();
        this.password = responseGrpc.getPassword();
    }
}
