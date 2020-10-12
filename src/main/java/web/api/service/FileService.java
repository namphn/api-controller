package web.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.rpc.user.SaveUserAvatarResponse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
public class FileService {
    @Value( "${path.avatar}")
    private String avatarStore;
    private final Path root = Paths.get("images");

    @Autowired
    private GrpcClientUserService grpcClientUserService;

    public ResponseEntity uploadAvatar(MultipartFile file, String userId) {
        ResponseBase responseBase = new ResponseBase();
        String savedPath = saveFile(file, avatarStore, userId);
        SaveUserAvatarResponse saveUserAvatarResponse = null;

        if(savedPath != null) {
            try {
                saveUserAvatarResponse = grpcClientUserService.SetUserAvatar(savedPath);
            } catch (Exception e) {
                responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
                responseBase.setStatus(Status.CAN_NOT_SAVE_FILE);
            }
        } else {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.CAN_NOT_SAVE_FILE);
        }

        if(saveUserAvatarResponse.getStatus().equals(Status.SUCCESS)) {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(Status.SUCCESS);
        }

        return new ResponseEntity(responseBase, HttpStatus.OK);
    }
    private String saveFile(MultipartFile file, String folderPath, String userId) {
        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource(".") + folderPath + userId + LocalDateTime.now().toString().replace(":","").replace(".","") + ".jpg";
        try {
            Path copyLocation = Paths
                    .get(path);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            return null;
        }
        return path;
    }
}
