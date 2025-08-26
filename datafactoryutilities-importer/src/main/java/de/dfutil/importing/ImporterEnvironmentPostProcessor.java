package de.dfutil.importing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ImporterEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication application
    ) {
        boolean successionHandlingActivated = Boolean.parseBoolean(
                environment.getProperty("app.importer.inputsource.succession_handling_activated"));
        boolean resetSuccessionHandlingApplicationStateActivated = Boolean.parseBoolean(
                environment.getProperty("app.importer.inputsource.reset_succession_handling_application_state_activated"));
        if (successionHandlingActivated
                && resetSuccessionHandlingApplicationStateActivated) {
            throw new IllegalStateException(
                    "Following properties mutually exclude each other to be true:\n" +
                            "successionHandlingActivated\n" +
                            "resetSuccessionHandlingApplicationStateActivated"
            );
        }
    }

}