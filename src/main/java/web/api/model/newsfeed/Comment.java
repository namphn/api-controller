package web.api.model.newsfeed;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
    private String userId;
    private String commentContent;
    private String userAvatar;
    private List<Comment> childComment;
    private LocalDateTime commentTime;
}
