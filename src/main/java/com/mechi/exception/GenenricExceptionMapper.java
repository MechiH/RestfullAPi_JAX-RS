package com.mechi.exception;

import com.mechi.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

// to enable genericException uncomment the provider
// @Provider
public class GenenricExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        ErrorMessage message = new ErrorMessage(exception.getMessage(), 500,
                "https://www.youtube.com/watch?v=9oeJc_VkZxo&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=28");
        return Response.status(Status.NOT_FOUND).entity(message).build();
    }

}
