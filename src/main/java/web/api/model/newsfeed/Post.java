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
    private long id;
    private long userId;
    private String content;
    private List<Comment> comment;
    private List<String> likes;
    private List<Share> share;
    private List<String> tags;
}
