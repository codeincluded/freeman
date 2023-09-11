package com.github.codeincluded.freeman.data;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Request {
    private String method;
    private String url;
    private String body;
    private Map<String, String> headers;
}
