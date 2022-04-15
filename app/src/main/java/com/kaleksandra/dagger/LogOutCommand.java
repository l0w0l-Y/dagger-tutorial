package com.kaleksandra.dagger;

import java.util.List;

import javax.inject.Inject;

final class LogOutCommand implements Command {
    private final Outputter outputter;

    @Inject
    LogOutCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public Result handleInput(List<String> input) {
        if (!input.isEmpty()) return Result.invalid();
        outputter.output(String.format("user has been logged out"));
        return Result.inputCompleted();
    }
}
