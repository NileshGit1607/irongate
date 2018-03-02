package com.irongate.web.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity {
    ResponseStatus status;
    Object data;
    String error;
}
