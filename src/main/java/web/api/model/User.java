package web.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String password;
    private String email;
    private String userName;
    private String phone;

    @Getter @Setter
    private boolean enable;

    public User(String email, String password){
        this.password = password;
        this.email = email;
    }



}
