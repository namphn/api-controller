package web.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
public class FileService {
    @Value( "${path.avatar}")
    private String avatarStore;

    public ResponseEntity uploadAvatar(MultipartFile file, String userId) {
        ResponseBase responseBase = new ResponseBase();
        if(saveFile(file,avatarStore, userId) != null) {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(Status.SUCCESS);
        } else {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.CAN_NOT_SAVE_FILE);
        }
        return new ResponseEntity(responseBase, HttpStatus.OK);
    }
    private String saveFile(MultipartFile file, String folderPath, String userId) {
        String path = folderPath + userId + LocalDateTime.now().toString();
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
