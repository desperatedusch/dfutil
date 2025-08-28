package de.dfutil.downloading;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;

@ConfigurationProperties(prefix = "app.downloader")
public class DatafactoryDownloaderConfigurationProperties {


    private String resourceUrl;
    private String targetDestinationArchived;
    private String user;
    private String password;
    private String targetDestinationUnzipped;
    private String targetFolder;
    private String archivedTargetDestination;
    private String targetDestination;

    public String getArchivedTargetDestination() {
        return this.archivedTargetDestination;
    }

    public void setArchivedTargetDestination(final String archivedTargetDestination) {
        this.archivedTargetDestination = archivedTargetDestination;
    }

    public String getTargetDestination() {
        return this.targetDestination;
    }

    public void setTargetDestination(final String targetDestination) {
        this.targetDestination = targetDestination;
    }

    public String getTargetFolder() {
        return this.targetFolder;
    }

    public void setTargetFolder(final String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public String getResourceUrl() {
        return this.resourceUrl;
    }

    public void setResourceUrl(@NonNull final String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getTargetDestinationArchived() {
        return this.targetDestinationArchived;
    }

    public void setTargetDestinationArchived(@NonNull final String targetDestinationArchived) {
        this.targetDestinationArchived = targetDestinationArchived;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(@NonNull final String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(@NonNull final String password) {
        this.password = password;
    }

    public String getTargetDestinationUnzipped() {
        return this.targetDestinationUnzipped;
    }

    public void setTargetDestinationUnzipped(@NonNull final String targetDestinationUnzipped) {
        this.targetDestinationUnzipped = targetDestinationUnzipped;
    }

}
