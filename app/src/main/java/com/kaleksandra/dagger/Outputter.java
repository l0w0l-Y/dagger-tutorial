package com.kaleksandra.dagger;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

public interface Outputter {
    void output(String output);
}

class ConsoleOutputter implements Outputter {
    @Inject
    ConsoleOutputter() {
    }

    @Override
    public void output(String output) {
        System.out.println(output);
    }
}

@Module
abstract class OutputterModule {
    @Provides
    static Outputter outPutter(ConsoleOutputter outPutter) {
        return System.out::println;
    }
}