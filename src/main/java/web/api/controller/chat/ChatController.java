package web.api.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import web.api.model.chat.ChatMessage;
import web.api.model.request.GetChanelRequest;
import web.api.model.response.GetChanelResponse;
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
//        this.chatService.submitMessage(chatMessage);
        return chatMessage;
    }

//    @PutMapping(value="/channel",produces="application/json", consumes="application/json")
//    public ResponseEntity establishChatChannel(@RequestBody GetChanelRequest request) {
//        GetChanelResponse response = null;
//        try {
//            GetChanelResponse response = grpcClientChatService.getChannel(request);
//        }
//
//
//    }
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
