package com.kaleksandra.dagger;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UserCommandsRouter.InstallationModule.class, HelloWorldModule.class, LoginCommandModule.class, OutputterModule.class, AmountsModule.class})
interface CommandProcessorFactory {
    CommandProcessor commandProcessor();

    static CommandProcessorFactory create() {
        return DaggerCommandProcessorFactory.create();
    }
}

