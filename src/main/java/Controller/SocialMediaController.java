
package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController
{
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    AccountService accountService = new AccountService();
    MessageService messageService = new MessageService();

    public Javalin startAPI()
    {
        Javalin app = Javalin.create();

        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::addMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserHandler);

        return app;
    }

    private void registerHandler(Context ctx)
    {
        Account account = ctx.bodyAsClass(Account.class);
        Account newAccount = accountService.register(account);
        
        if (newAccount != null)
        {
            ctx.json(newAccount);
        }
        else
        {
            ctx.status(400);
        }
    }

    private void loginHandler(Context ctx)
    {
        Account account = ctx.bodyAsClass(Account.class);
        Account loggedIn = accountService.login(account);
        
        if (loggedIn != null)
        {
            ctx.json(loggedIn);
        }
        else
        {
            ctx.status(401);
        }
    }

    private void addMessageHandler(Context ctx)
    {
        Message message = ctx.bodyAsClass(Message.class);
        Message createdMessage = messageService.addMessage(message);
        
        if (createdMessage != null)
        {
            ctx.json(createdMessage);
        }
        else
        {
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx)
    {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void getMessageByIdHandler(Context ctx)
    {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);
        
        if (message != null)
        {
            ctx.json(message);
        }
        else
        {
            ctx.status(200);
        }
    }

    private void deleteMessageHandler(Context ctx)
    {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessage(messageId);
        
        if (deletedMessage != null)
        {
            ctx.json(deletedMessage);
        }
        else
        {
            ctx.status(200); // still 200 with empty body
        }
    }

    private void updateMessageHandler(Context ctx)
    {
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message messageUpdate = ctx.bodyAsClass(Message.class);
        Message updatedMessage = messageService.updateMessage(messageId, messageUpdate.getMessage_text());
        
        if (updatedMessage != null)
        {
            ctx.json(updatedMessage);
        }
        else
        {
            ctx.status(400);
        }
    }

    private void getMessagesByUserHandler(Context ctx)
    {
        int accountId = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> userMessages = messageService.getMessagesByUser(accountId);
        ctx.json(userMessages);
    }


}