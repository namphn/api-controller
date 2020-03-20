package web.api.model.newsfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import web.service.grpc.newsfeed.GetNewsFeedResponse;

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
    private List<Long> likes;
    private List<Share> share;
    private List<Long> tags;
}
