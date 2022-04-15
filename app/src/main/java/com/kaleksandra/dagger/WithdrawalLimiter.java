package com.kaleksandra.dagger;

import java.math.BigDecimal;

import javax.inject.Inject;

@PerSession
final class WithdrawalLimiter {
    private BigDecimal remainingWithdrawalLimit;

    @Inject
    WithdrawalLimiter(@MaximumWithdrawal BigDecimal maximumWithdrawal) {
        this.remainingWithdrawalLimit = maximumWithdrawal;
    }

    void recordDeposit(BigDecimal amount) {
        this.remainingWithdrawalLimit = this.remainingWithdrawalLimit.add(amount);
    }

    void recordWithdrawal(BigDecimal amount) {
        this.remainingWithdrawalLimit = this.remainingWithdrawalLimit.subtract(amount);
    }

    BigDecimal remainingWithdrawalLimit() {
        return remainingWithdrawalLimit;
    }
}
