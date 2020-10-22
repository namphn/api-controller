package web.api.controller.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.model.request.AddFollowerRequest;
import web.api.service.GrpcClientFollowService;

import javax.validation.Valid;

@RestController
@RequestMapping("followers")
public class FollowerController {

    @Autowired
    private GrpcClientFollowService followService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity getFollow(@PathVariable String userId) {
        return new ResponseEntity(followService.getAllFollower(userId, 1), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity addFollower(@PathVariable String userId, @RequestBody @Valid AddFollowerRequest request ) {
        return new ResponseEntity(followService.addFollow(userId, request.getFollowerId(), true), HttpStatus.OK);
    }
}
