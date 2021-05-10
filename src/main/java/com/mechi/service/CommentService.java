package com.mechi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mechi.database.DatabaseClass;
import com.mechi.model.Comment;
import com.mechi.model.ErrorMessage;
import com.mechi.model.Message;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class CommentService {
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(Long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    // Not recommended to put Errorr code here cause it's related to the
    // presentation (it's presented to the user) not to put in the bussniess logique
    public Comment getComment(Long messageId, Long commentId) {
        Message message = messages.get(messageId);
        ErrorMessage Errormessage = new ErrorMessage("Not Found", 404, "https://www.youtube.com");
        Response response = Response.status(Status.NOT_FOUND).entity(Errormessage).build();
        if (message == null) {
            throw new WebApplicationException(response);
        }
        Map<Long, Comment> comments = message.getComments();
        Comment comment = comments.get(commentId);
        if (comment == null) {
            throw new NotFoundException(response); // u can use it without indicating the status code in the response
        }
        return comment;
    }

    public Comment addComment(Long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId((long) (comments.size() + 1));
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(Long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(Long messageId, Long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);

    }
}
