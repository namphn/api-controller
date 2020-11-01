package web.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class LoginResponse implements Serializable {
    private String token;
    private String userId;

    public LoginResponse(web.api.rpc.user.LoginResponse response){
        this.token = response.getToken();
        this.userId = response.getUserId();
    }
}
