package com.kaleksandra.dagger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

final class CommandRouter {
    private final Map<String, Command> commands;
    private final Outputter outputter;

    Command.Result route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        List<String> args = splitInput.subList(1, splitInput.size());
        Command.Result result = command.handleInput(args);
        return result.status().equals(Command.Status.INVALID) ? invalidCommand(input) : result;
    }

    private Command.Result invalidCommand(String input) {
        outputter.output(String.format("couldn't understand \"%s\". please try again.", input));
        return Command.Result.invalid();
    }

    private static List<String> split(String input) {
        return Arrays.asList(input.trim().split("\\s+"));
    }

    @Inject
    CommandRouter(Map<String, Command> commands, Outputter outputter) {
        this.commands = commands;
        this.outputter = outputter;
    }
}
