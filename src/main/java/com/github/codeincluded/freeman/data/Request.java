package com.github.codeincluded.freeman.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private String method;
    private String url;
}
