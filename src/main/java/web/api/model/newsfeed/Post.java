package web.api.model.newsfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
    private List<Comment> comment;
    private List<String> likes;
    private List<Share> share;
    private List<String> tags;
}
