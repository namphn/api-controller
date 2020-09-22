package web.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class LoginResponse implements Serializable {
    private String token;
    private String status;
    private String username;
    private String userId;

    public LoginResponse(web.api.rpc.user.LoginResponse response){
        this.token = response.getToken();
        this.status = response.getStatus();
        this.username = response.getUsername();
        this.userId = response.getUserId();
    }
    public LoginResponse(String status) {
        this.status = status;
    }
}
