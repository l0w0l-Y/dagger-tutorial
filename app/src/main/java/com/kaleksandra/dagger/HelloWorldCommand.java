package com.kaleksandra.dagger;

import java.util.List;

import javax.inject.Inject;

final class HelloWorldCommand implements Command {
    @Inject
    HelloWorldCommand(Outputter outPutter) {
        this.outPutter = outPutter;
    }

    Outputter outPutter;

    @Override
    public String key() {
        return "hello";
    }

    @Override
    public Status handleInput(List<String> input) {
        if (!input.isEmpty()) {
            return Status.INVALID;
        }
        outPutter.output("world!");
        return Status.HANDLED;
    }
}
