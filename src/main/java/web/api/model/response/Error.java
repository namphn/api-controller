package web.api.model.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class Error {
    public static ErrorResponse HAVE_EXIST_ACCOUNT = new ErrorResponse("USER_001",
            "this email address is already being used by another account");

    public static ErrorResponse INVALID_EMAIL = new ErrorResponse("USER_002", "invalid email");
    public static ErrorResponse INTERNAL_SERVER = new ErrorResponse("SERVER_R_001",
            "Something went wrong. Please contact us about this problem");
}
