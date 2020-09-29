package web.api.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import web.api.model.chat.ChatMessage;
import web.api.model.request.GetChanelRequest;
import web.api.model.response.GetChanelResponse;
import web.api.model.response.ResponseBase;
import web.api.model.response.Status;
import web.api.rpc.chat.GetChannelResponse;
import web.api.service.GrpcClientChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private GrpcClientChatService grpcClientChatService;

    @MessageMapping("/private.chat.{channelId}")
    @SendTo("/topic/private.chat.{channelId}")
    public ChatMessage chatMessage(@DestinationVariable String channelId, ChatMessage chatMessage) {
        this.grpcClientChatService.submitMessage(chatMessage);
        return chatMessage;
    }

    @PostMapping(value="/channel",produces="application/json", consumes="application/json")
    public ResponseEntity establishChatChannel(@RequestBody GetChanelRequest request) {
        GetChanelResponse response = null;
        ResponseBase responseBase = new ResponseBase();
        try {
            response = grpcClientChatService.getChannel(request);
        } catch (Exception e) {
            responseBase.setStatusCode(Status.StatusCode.SERVER_ERROR);
            responseBase.setStatus(Status.INTERNAL_SERVER);
        }

        if(response != null) {
            responseBase.setStatusCode(Status.StatusCode.NORMAL);
            responseBase.setStatus(Status.SUCCESS);
            responseBase.setData(response);
        } else {
            responseBase.setStatusCode(Status.StatusCode.NODATA);
            responseBase.setStatus(Status.NODATA);
        }

        return new ResponseEntity(responseBase, HttpStatus.OK);
    }
    /**
     * todo getMessage
     */
//
//    @RequestMapping(value="/api/private-chat/channel/{channelUuid}", method= RequestMethod.GET, produces="application/json")
//    public ResponseEntity<String> getExistingChatMessages(@PathVariable("channelUuid") String channelUuid, int page) {
//        List<ChatMessage> messages = chatService.getAllExistingChatMessage(channelUuid, page);
//
//        if(messages != null && !messages.isEmpty())
//        {
//            return new ResponseEntity(messages, HttpStatus.OK);
//        }
//
//        else {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }

}
