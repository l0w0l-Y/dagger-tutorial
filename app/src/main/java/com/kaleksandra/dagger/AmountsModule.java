package com.kaleksandra.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

@Module
interface AmountsModule {
    @Provides
    @MinimumBalance
    static BigDecimal minimumBalance() {
        return BigDecimal.ZERO;
    }

    @Provides
    @MaximumBalance
    static BigDecimal maximumWithdrawal() {
        return new BigDecimal(1000);
    }
}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface MinimumBalance {}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface MaximumBalance {}
