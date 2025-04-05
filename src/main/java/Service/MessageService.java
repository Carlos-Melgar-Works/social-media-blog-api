package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService
{
    private MessageDAO messageDAO;

    public MessageService()
    {
        this.messageDAO = new MessageDAO();
    }

    // Create new message
    public Message createMessage(Message message)
    {
        if (message.getMessage_text() == null || message.getMessage_text().isBlank())
        {
            return null;
        }

        if (message.getMessage_text().length() > 255)
        {
            return null;
        }

        return messageDAO.insertMessage(message);
    }

    // Get all messages
    public List<Message> getAllMessages()
    {
        return messageDAO.getAllMessages();
    }

    // Get a message by ID
    public Message getMessageById(int message_id)
    {
        return messageDAO.getMessageById(message_id);
    }

    // Delete a message
    public Message deleteMessageById(int message_id)
    {
        return messageDAO.deleteMessageById(message_id);
    }

    // Update message
    public Message updateMessage(int message_id, String newText)
    {
        if (newText == null || newText.isBlank() || newText.length() > 255)
        {
            return null;
        }

        return messageDAO.updateMessageText(message_id, newText);
    }

    // Get messages by user
    public List<Message> getMessagesByAccountId(int account_id)
    {
        return messageDAO.getMessagesByAccountId(account_id);
    }
}
