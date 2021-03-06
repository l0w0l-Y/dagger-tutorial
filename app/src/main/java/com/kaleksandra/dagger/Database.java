package com.kaleksandra.dagger;

import android.annotation.TargetApi;
import android.os.Build;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class Database {
    private final Map<String, Account> accounts = new HashMap<>();

    @Inject
    Database() {
    }

    @TargetApi(Build.VERSION_CODES.N)
    Account getAccount(String username) {
        return accounts.computeIfAbsent(username, Account::new);
    }

    static final class Account {
        private final String username;
        private BigDecimal balance = BigDecimal.ZERO;

        Account(String username) {
            this.username = username;
        }

        String username() {
            return username;
        }

        BigDecimal balance() {
            return balance;
        }

        void deposit(BigDecimal deposit) {
            this.balance = this.balance.add(deposit);
        }

        void withdraw(BigDecimal deposit) {
            this.balance = this.balance.subtract(deposit);
        }
    }
}
