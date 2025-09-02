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
        return archivedTargetDestination;
    }

    public void setArchivedTargetDestination(String archivedTargetDestination) {
        this.archivedTargetDestination = archivedTargetDestination;
    }

    public String getTargetDestination() {
        return targetDestination;
    }

    public void setTargetDestination(String targetDestination) {
        this.targetDestination = targetDestination;
    }

    public String getTargetFolder() {
        return targetFolder;
    }

    public void setTargetFolder(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(@NonNull String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getTargetDestinationArchived() {
        return targetDestinationArchived;
    }

    public void setTargetDestinationArchived(@NonNull String targetDestinationArchived) {
        this.targetDestinationArchived = targetDestinationArchived;
    }

    public String getUser() {
        return user;
    }

    public void setUser(@NonNull String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getTargetDestinationUnzipped() {
        return targetDestinationUnzipped;
    }

    public void setTargetDestinationUnzipped(@NonNull String targetDestinationUnzipped) {
        this.targetDestinationUnzipped = targetDestinationUnzipped;
    }

}
