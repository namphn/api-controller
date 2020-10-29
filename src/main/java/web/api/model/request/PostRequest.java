package web.api.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostRequest {
    private MultipartFile image;
    private String userId;
    private String content;
}
