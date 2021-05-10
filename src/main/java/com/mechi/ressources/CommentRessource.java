package com.mechi.ressources;

import java.util.List;

import com.mechi.model.Comment;
import com.mechi.service.CommentService;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/") // is optional for sub ressources
public class CommentRessource {
    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> gettAllComments(@PathParam("messageId") Long messageId) {
        return commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId) {
        return commentService.getComment(messageId, commentId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") Long messageId, Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId,
            Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment deleteComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId) {
        return commentService.removeComment(messageId, commentId);
    }

}
