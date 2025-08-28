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
        return this.parsingActivated;
    }

    public void setParsingActivated(final boolean parsingActivated) {
        this.parsingActivated = parsingActivated;
    }

    public boolean isSuccessionHandlingActivated() {
        return this.successionHandlingActivated;
    }

    public void setSuccessionHandlingActivated(final boolean successionHandlingActivated) {
        this.successionHandlingActivated = successionHandlingActivated;
    }

    @NonNull
    public String getInputFolders() {
        return this.inputFolders;
    }

    public void setInputFolders(@NonNull final String inputFolders) {
        this.inputFolders = inputFolders;
    }

    @NonNull
    public String getMainFileName() {
        return this.mainFileName;
    }

    public void setMainFileName(@NonNull final String mainFileName) {
        this.mainFileName = mainFileName;
    }

    public boolean isDeleteSourcesAfterSuccessfulProcessing() {
        return this.deleteSourcesAfterSuccessfulProcessing;
    }

    public void setDeleteSourcesAfterSuccessfulProcessing(final boolean deleteSourcesAfterSuccessfulProcessing) {
        this.deleteSourcesAfterSuccessfulProcessing = deleteSourcesAfterSuccessfulProcessing;
    }

    public boolean isResetSuccessionHandlingApplicationStateActivated() {
        return this.resetSuccessionHandlingApplicationStateActivated;
    }

    public void setResetSuccessionHandlingApplicationStateActivated(final boolean resetSuccessionHandlingApplicationStateActivated) {
        this.resetSuccessionHandlingApplicationStateActivated = resetSuccessionHandlingApplicationStateActivated;
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public void setBatchSize(final int batchSize) {
        this.batchSize = batchSize;
    }

}
