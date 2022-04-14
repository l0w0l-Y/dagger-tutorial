package com.kaleksandra.dagger;

import javax.inject.Inject;

final class LoginCommand extends SingleArgCommand {
    private final Outputter outputter;

    @Inject
    LoginCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public String key() {
        return "login";
    }

    @Override
    protected Status handleArg(String arg) {
        outputter.output(arg + " is logged in.");
        return Status.HANDLED;
    }
}