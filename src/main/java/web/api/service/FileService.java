package web.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.model.response.UploadAvatarResponse;
import web.api.rpc.user.SaveUserAvatarResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
public class FileService {
    @Value( "${path.avatar}")
    private String avatarStore;

    @Autowired
    private GrpcClientUserService grpcClientUserService;

    public ResponseEntity uploadAvatar(MultipartFile file, String userId) {
        ResponseBase responseBase = new ResponseBase();
        String savedPath = saveFile(file, avatarStore, userId);
        SaveUserAvatarResponse saveUserAvatarResponse = null;

        if(savedPath != null) {
            try {
                saveUserAvatarResponse = grpcClientUserService.SetUserAvatar(savedPath, userId);
            } catch (Exception e) {
                responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
                responseBase.setStatus(Status.CAN_NOT_SAVE_FILE);
            }
        } else {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.CAN_NOT_SAVE_FILE);
        }
        if(saveUserAvatarResponse == null ) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.CAN_NOT_SAVE_FILE);
        }
        else if(saveUserAvatarResponse.getStatus().equals(Status.SUCCESS)) {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(Status.SUCCESS);
            responseBase.setData(new UploadAvatarResponse(savedPath));
        }

        return new ResponseEntity(responseBase, HttpStatus.OK);
    }
    private String saveFile(MultipartFile file, String folderPath, String userId) {
        final Path root = Paths.get(folderPath);
        String fileName = userId + LocalDateTime.now().toString().replace(":","").replace(".","") + ".jpg";
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            try {
                Files.getFileStore(root);
            } catch (IOException ioException) {
                return null;
            }
        }
        try {
            Files.copy(file.getInputStream(), root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            return null;
        }
        return fileName;
    }
}
