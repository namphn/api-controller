package web.api.controller.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.service.GrpcClientFollowService;

@RestController
@RequestMapping("following")
public class FollowingController {
    @Autowired
    private GrpcClientFollowService followService;

    @GetMapping
    @RequestMapping("/{userId}")
    public ResponseEntity getFollow(@PathVariable String userId) {
        return new ResponseEntity(followService.getAllFollower(userId, 2), HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/add/{userId}")
    public ResponseEntity getFollow(@PathVariable String userId, @RequestBody String followingId) {
        return new ResponseEntity(followService.addFollow(userId, followingId, false), HttpStatus.OK);
    }
}
