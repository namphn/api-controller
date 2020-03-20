package web.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class LoginResponse implements Serializable {
    private String token;
    private String status;

    public LoginResponse(web.service.grpc.user.LoginResponse response){
        this.token = response.getToken();
        this.status = response.getStatus();
    }
}
