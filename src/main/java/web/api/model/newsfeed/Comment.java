package web.api.model.newsfeed;

import lombok.Data;

@Data
public class Comment {
    private long userId;
    private String commentContent;

}
