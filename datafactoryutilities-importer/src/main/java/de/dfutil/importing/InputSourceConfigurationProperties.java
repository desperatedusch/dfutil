package de.dfutil.importing;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;

@ConfigurationProperties(prefix = "app.importer.inputsource")
public class InputSourceConfigurationProperties {

    private String inputFolders;
    private String mainFileName;
    private boolean deleteSourcesAfterSuccessfulProcessing;
    private boolean parsingActivated;
    private boolean successionhandlingActivated;

    public boolean isParsingActivated() {
        return parsingActivated;
    }

    public void setParsingActivated(boolean parsingActivated) {
        this.parsingActivated = parsingActivated;
    }

    public boolean isSuccessionhandlingActivated() {
        return successionhandlingActivated;
    }

    public void setSuccessionhandlingActivated(boolean successionhandlingActivated) {
        this.successionhandlingActivated = successionhandlingActivated;
    }

    @NonNull
    public String getInputFolders() {
        return inputFolders;
    }

    public void setInputFolders(@NonNull String inputFolders) {
        this.inputFolders = inputFolders;
    }

    @NonNull
    public String getMainFileName() {
        return mainFileName;
    }

    public void setMainFileName(@NonNull String mainFileName) {
        this.mainFileName = mainFileName;
    }

    public boolean isDeleteSourcesAfterSuccessfulProcessing() {
        return deleteSourcesAfterSuccessfulProcessing;
    }

    public void setDeleteSourcesAfterSuccessfulProcessing(boolean deleteSourcesAfterSuccessfulProcessing) {
        this.deleteSourcesAfterSuccessfulProcessing = deleteSourcesAfterSuccessfulProcessing;
    }

}
