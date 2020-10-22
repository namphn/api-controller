package web.api.model.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessage {

    private String content;
    private String userId1;
    private String userId2;
    private LocalDateTime dateTime = LocalDateTime.now();;

    public ChatMessage(String content, String userId1, String userId2) {
        this.content = content;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}
