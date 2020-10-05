package web.api.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GetFollowerAndFollowingResponse {
    private List<String> followers;
    private  List<String> followings;
}
