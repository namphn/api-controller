package web.api.controller.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.model.request.AddFollowingRequest;
import web.api.service.GrpcClientFollowService;

import javax.validation.Valid;

@RestController
@RequestMapping("followings")
public class FollowingController {
    @Autowired
    private GrpcClientFollowService followService;

    @GetMapping
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity getFollow(@PathVariable String userId) {
        return new ResponseEntity(followService.getAllFollower(userId, 2), HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping( value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity getFollow(@PathVariable String userId, @RequestBody @Valid AddFollowingRequest request) {
        return new ResponseEntity(followService.addFollow(userId, request.getFollowingId(), false), HttpStatus.OK);
    }
}
