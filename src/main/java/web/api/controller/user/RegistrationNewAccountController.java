package web.api.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.api.model.request.RegistrationInformationRequest;
import web.api.model.request.RegistrationRequest;
import web.api.model.response.RegistrationResponse;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.service.FileService;
import web.api.service.GrpcClientUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class RegistrationNewAccountController {
    private final GrpcClientUserService grpcClientService;
    private final FileService fileService;

    public RegistrationNewAccountController(GrpcClientUserService grpcClientService, FileService fileService) {
        this.grpcClientService = grpcClientService;
        this.fileService = fileService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegistrationRequest request){
        ResponseBase responseBase = new ResponseBase();
        RegistrationResponse response = null;

        try {
            response = grpcClientService.registerNewAccount(request);
        } catch (Exception e) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.INTERNAL_SERVER);
        }

        if(response == null) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.INTERNAL_SERVER);
            return new ResponseEntity(responseBase, HttpStatus.OK);
        }

        if(!response.getStatus().equals(Status.SENT_EMAIL)) {
            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(response.getStatus());
            responseBase.setData(response);
        } else{
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(response.getStatus());
        }
        return new ResponseEntity(responseBase, HttpStatus.OK);
    }

    @PostMapping("/register-information")
    public ResponseEntity registerInformationAccount(@Valid @RequestBody RegistrationInformationRequest request){
        String response = grpcClientService.registerInformation(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping("/{userId}/avatar")
    public ResponseEntity uploadUserAvatar(@RequestParam("file") MultipartFile file, @PathVariable String userId){
        return fileService.uploadAvatar(file,userId);
    }
}
