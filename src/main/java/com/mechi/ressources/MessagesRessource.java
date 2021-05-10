package com.mechi.ressources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.mechi.service.MessageService;
import com.mechi.exception.DataNotFoundException;
import com.mechi.model.Message;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
// import jakarta.ws.rs.core.Response.Status;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("messages") // annotate (map) MessageRessource to messages path
public class MessagesRessource {

    MessageService messageService = new MessageService();

    /**
     * Method handling HTTP GET requests. The returned object will be sent to the
     * client as "text/plain" media type.f
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML }) // format of return content type f
    public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
            @QueryParam("size") int size) {// if query param dosen't exist that it returns 0
        if (year > 0) {
            return messageService.gettAllMessagesForYear(year);
        }
        if (size >= 0 && start >= 0) {
            return messageService.gettAllMessagesPaginated(start, size);
        }
        return messageService.gettAllMessages();
    }

    @GET
    @Path("/{messageId}")
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    public Message getMessage(@PathParam("messageId") Long messaegId, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(messaegId);
        if (message == null) {
            throw new DataNotFoundException("Message with id " + messaegId + " not found");
        }

        message.addlink(getUriForSelf(uriInfo, message), "self");
        message.addlink(getUriForProfile(uriInfo, message), "profile");
        message.addlink(getUriForComments(uriInfo, message), "profile");
        return message;

    }

    // the arguement : recieve json convert to Message
    // return messageService.addMessage(message);
    // to add status code we reyurn response
    // u can add things to absolute path u get in our cas we will concatenate the id
    // create an instane of the response using build or .status(Status.CREATED)
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // to specifi that this method consume a json
    @Produces(MediaType.APPLICATION_JSON) // format of return content type f
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(newMessage).build();

    }

    @PUT
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message updateMessage(Message message, @PathParam("messageId") Long messaegId) {
        message.setId(messaegId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteMessage(@PathParam("messageId") Long messaegId) {
        messageService.removeMessage(messaegId);
    }

    // implementing comments
    // but not ideal to put commentRessources
    // ! implement a sub ressource (take the path to fire a functio in another
    // location)
    // @GET -- we want this action to happen to all ressources
    @Path("/{messageId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public CommentRessource CommentsRessources() {
        return new CommentRessource();
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder().path(MessagesRessource.class)
                .path(MessagesRessource.class, "CommentsRessources").path(CommentRessource.class)
                .resolveTemplate("messageId", message.getId()).build().toString();
        return uri;
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder()// http://localhost:8080/work/webapi
                .path(ProfileRessource.class) // -- /profiles
                .path(message.getAuthor())// --/{authorName}
                .build().toString();
        return uri;
    }

    private String getUriForSelf(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder()// http://localhost:8080/work/webapi
                .path(MessagesRessource.class) // -- /messages
                .path(Long.toString(message.getId()))// --/{messageId}
                .build().toString();
        return uri;
    }

}
