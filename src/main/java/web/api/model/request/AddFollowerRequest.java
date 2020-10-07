package web.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddFollowerRequest {
    @NotNull
    private String followerId;
}
