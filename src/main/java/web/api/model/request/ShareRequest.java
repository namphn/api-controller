package web.api.model.request;

import lombok.Getter;
import web.api.model.request.base.ReactRequest;

@Getter
public class ShareRequest extends ReactRequest {
    private String content;
}
