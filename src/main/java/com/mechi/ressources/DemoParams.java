package com.mechi.ressources;

import com.mechi.service.BeanFilter;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("demo")
public class DemoParams {
    @GET
    @Path("matrix")
    @Produces(MediaType.TEXT_PLAIN)
    // matrix param as path param jyst in placeof ?params its ;params
    // access header params for security and authentication
    // cookie param
    // @formParam to acces form parameters not widely used coz we rarely submit data
    // to rest API
    public String getMessage(@MatrixParam("msg") String msg, @HeaderParam("headerParam") String header,
            @CookieParam("cookie") String cookie) {
        return "Msg : " + msg + "Header : " + header + "Cookie : " + cookie;
    }

    // to acess values using context to avoid tto memorise names of params
    // @Context used with some types of annotation
    // UriInfo has a lot of functions
    @GET
    @Path("paramsContext")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        String path = uriInfo.getAbsolutePath().toString(); // http://localhost:8080/work/webapi/demo/paramsContext
        String cookie = headers.getCookies().toString();
        return path + " " + cookie;
    }

    // Another option to avoid this hug amount of params is to use beanParam
    @GET
    @Path("beanParams")
    public int getBeanParams(@BeanParam BeanFilter bean) {
        return bean.getParam1() + bean.getParam2() + bean.getParam3();
    }
}
