package com.kaleksandra.dagger;

import javax.inject.Inject;

final class LoginCommand extends SingleArgCommand {
    private final Database database;
    private final Outputter outputter;
    private final UserCommandsRouter.Factory userCommandsRouterFactory;

    @Inject
    LoginCommand(
            Database database,
            Outputter outputter,
            UserCommandsRouter.Factory userCommandsRouterFactory) {
        this.database = database;
        this.outputter = outputter;
        this.userCommandsRouterFactory = userCommandsRouterFactory;
    }

    @Override
    public Result handleArg(String username) {
        Database.Account account = database.getAccount(username);
        outputter.output(
                username + " is logged in with balance: " + account.balance());
        return Result.enterNestedCommandSet(
                userCommandsRouterFactory.create(account).router());
    }
}