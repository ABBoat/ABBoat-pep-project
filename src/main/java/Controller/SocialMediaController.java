package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
//import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class SocialMediaController {
    AccountService accountService;
    //MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        //this.messageService = new MessageService();
    }

    //Endpoints 
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::logintoAccountHandler);

        return app;
    }

    //Endpoint handlers
    private void postAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if (addedAccount == null) {
            ctx.status(400);
        }
        else {
            ctx.json(om.writeValueAsString(addedAccount));
        }
    }
    
    private void logintoAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account validAccount = accountService.validateAccount(account);
        if (validAccount == null) {
            ctx.status(401);
        }
        else {
            ctx.json(om.writeValueAsString(validAccount));
        }
    }
}