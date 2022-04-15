package com.kaleksandra.dagger;

import java.math.BigDecimal;

import javax.inject.Inject;

/**
 * Deposits money to the ATM.
 */
final class DepositCommand extends BigDecimalCommand {
    private final Outputter outputter;
    private final Database.Account account;

    @Inject
    DepositCommand(Outputter outputter, Database.Account account) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        outputter.output("your new balance is: " + account.balance());
    }
}
