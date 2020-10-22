package web.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddFollowingRequest {
    @NotNull
    private String followingId;
}
