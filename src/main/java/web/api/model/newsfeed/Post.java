package web.api.model.newsfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private String id;
    private String userId;
    private String content;
    private String userAvatar;
    private String image;
    private String userName;
    private LocalDateTime postTime;
    private List<Comment> comment;
    private List<String> likes;
    private List<Share> share;
    private List<String> tags;
}
