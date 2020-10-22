package web.api.model.response;

import lombok.Data;

@Data
public class ResponseBase {
    private String statusCode;
    private String status;
    private Object data;
 }
