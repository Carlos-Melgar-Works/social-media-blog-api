
package Service;

import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Message;

import java.util.List;

public class MessageService
{
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService()
    {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    // Create new message
    public Message addMessage(Message message)
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
    public Message deleteMessage(int message_id)
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
    public List<Message> getMessagesByUser(int account_id)
    {
        return messageDAO.getMessagesByAccountId(account_id);
    }
}
