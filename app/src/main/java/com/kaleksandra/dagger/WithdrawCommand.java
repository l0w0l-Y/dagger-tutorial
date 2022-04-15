package com.kaleksandra.dagger;

import java.math.BigDecimal;

import javax.inject.Inject;

final class WithdrawCommand extends BigDecimalCommand {
    private final Database.Account account;
    private final Outputter outputter;
    private final BigDecimal minimumBalance;
    private final WithdrawalLimiter withdrawalLimiter;

    @Inject
    WithdrawCommand(
            Outputter outputter,
            Database.Account account,
            @MinimumBalance BigDecimal minimumBalance,
            WithdrawalLimiter withdrawalLimiter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
        this.minimumBalance = minimumBalance;
        this.withdrawalLimiter = withdrawalLimiter;
    }

    @Override
    public void handleAmount(BigDecimal amount) {
        BigDecimal remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit();
        if (amount.compareTo(remainingWithdrawalLimit) > 0) {
            // output error
            return;
        }

        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.compareTo(minimumBalance) < 0) {
            outputter.output("not enough money in the account");
        } else {
            account.withdraw(amount);
            outputter.output("your new balance is: " + account.balance());
            withdrawalLimiter.recordWithdrawal(amount);
        }
    }

}
