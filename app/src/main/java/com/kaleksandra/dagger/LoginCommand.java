package com.kaleksandra.dagger;

import java.util.Optional;

import javax.inject.Inject;

final class LoginCommand extends SingleArgCommand {
    private final Database database;
    private final Outputter outputter;
    private final UserCommandsRouter.Factory userCommandsRouterFactory;
    private final Optional<Database.Account> account;

    @Inject
    LoginCommand(
            Database database,
            Outputter outputter,
            UserCommandsRouter.Factory userCommandsRouterFactory,
            Optional<Database.Account> account) {
        this.database = database;
        this.outputter = outputter;
        this.userCommandsRouterFactory = userCommandsRouterFactory;
        this.account = account;
    }

    @Override
    public Result handleArg(String username) {
        if (account.isPresent()) {
            outputter.output(
                    "user " +
                            account.get().username() + " is already logged in");
            return Result.handled();
        }
        Database.Account account = database.getAccount(username);
        outputter.output(
                username + " is logged in with balance: " + account.balance());
        return Result.enterNestedCommandSet(
                userCommandsRouterFactory.create(account).router());
    }
}