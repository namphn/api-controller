package web.api.model.request;

import lombok.Data;

@Data
public class NewPasswordRequest {
    String newPassword;
    String newPasswordConfirm;
}
