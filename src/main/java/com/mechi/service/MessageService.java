package com.mechi.service;

import com.mechi.database.DatabaseClass;
import com.mechi.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MessageService {
    private static Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message((long) 1, "hello World", "Mechi"));
        messages.put(2L, new Message((long) 2f, "hello World", "Mechi"));
    }

    public List<Message> gettAllMessagesForYear(int year) {
        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> gettAllMessagesPaginated(int start, int size) {
        ArrayList<Message> list = new ArrayList<Message>(messages.values());
        return list.subList(start, start + size);
    }

    public List<Message> gettAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public Message getMessage(Long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId((long) (messages.size() + 1));
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(Long id) {
        return messages.remove(id);
    }
}
