package com.kaleksandra.dagger;

import java.util.List;

import javax.inject.Inject;

final class HelloWorldCommand implements Command {
    private final Outputter outputter;

    @Inject
    HelloWorldCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public Result handleInput(List<String> args) {
        if (!args.isEmpty()) {
            return Result.invalid();
        }
        outputter.output("howdy!");
        return Result.handled();
    }
}
