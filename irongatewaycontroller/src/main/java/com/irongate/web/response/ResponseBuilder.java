package com.irongate.web.response;

import javax.ws.rs.core.Response;

public class ResponseBuilder {

    public static Response buildSucessResponse(Object response) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatus(ResponseStatus.SUCCESS);
        responseEntity.setData(response);
        return Response.status(200).entity(responseEntity).build();
    }

    public static Response buildErrorResponse(String error) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatus(ResponseStatus.ERROR);
        responseEntity.setError(error);
        return Response.status(200).entity(responseEntity).build();
    }
}
