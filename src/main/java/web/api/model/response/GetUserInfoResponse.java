package web.api.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.api.model.user.UserPostList;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GetUserInfoResponse {
    private String userName;
    private String city;
    private String country;
    private String description;
    private String avatar;
    private List<String> following;
    private List<String> followers;
    private List<UserPostList> pots;
}
