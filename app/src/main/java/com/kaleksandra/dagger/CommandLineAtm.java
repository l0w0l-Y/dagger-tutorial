package com.kaleksandra.dagger;

import java.util.Scanner;

class CommandLineAtm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandProcessor commandProcessor = CommandProcessorFactory.create().commandProcessor();

        while (scanner.hasNextLine()) {
            Command.Status commandStatus = commandProcessor.process(scanner.nextLine());
            if (commandStatus.equals(Command.Status.INPUT_COMPLETED)) {
                break;
            }
        }
    }
}
