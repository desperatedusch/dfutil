package de.dfutil.importing;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;

@ConfigurationProperties(prefix = "app.importer.inputsource")
public class ImporterConfigurationProperties {

    private String inputFolders;
    private String mainFileName;
    private boolean deleteSourcesAfterSuccessfulProcessing;
    private boolean parsingActivated;
    private boolean successionHandlingActivated;
    private boolean resetSuccessionHandlingApplicationStateActivated;
    private int batchSize;

    public boolean isParsingActivated() {
        return parsingActivated;
    }

    public void setParsingActivated(boolean parsingActivated) {
        this.parsingActivated = parsingActivated;
    }

    public boolean isSuccessionHandlingActivated() {
        return successionHandlingActivated;
    }

    public void setSuccessionHandlingActivated(boolean successionHandlingActivated) {
        this.successionHandlingActivated = successionHandlingActivated;
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

    public boolean isResetSuccessionHandlingApplicationStateActivated() {
        return resetSuccessionHandlingApplicationStateActivated;
    }

    public void setResetSuccessionHandlingApplicationStateActivated(boolean resetSuccessionHandlingApplicationStateActivated) {
        this.resetSuccessionHandlingApplicationStateActivated = resetSuccessionHandlingApplicationStateActivated;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

}
