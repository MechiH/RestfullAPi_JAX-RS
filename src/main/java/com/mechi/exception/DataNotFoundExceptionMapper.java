package com.mechi.exception;

import com.mechi.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

//Map Erros to responses in jax-rs || every expcetion mapper needs to implement EceptionMapper
@Provider // register this class so that jax-rs know that this cass exist
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException exception) {
        ErrorMessage message = new ErrorMessage(exception.getMessage(), 404,
                "https://www.youtube.com/watch?v=9oeJc_VkZxo&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=28");
        return Response.status(Status.NOT_FOUND).entity(message).build();
    }

}
