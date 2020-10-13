package web.api.model.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadAvatarRequest {
    // Upload files.
    private MultipartFile[] avatarImage;
}
