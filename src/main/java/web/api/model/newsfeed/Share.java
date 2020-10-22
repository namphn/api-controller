package web.api.model.newsfeed;

import lombok.Data;

@Data
public class Share {
    private long userId;
    private String shareContent;

}
