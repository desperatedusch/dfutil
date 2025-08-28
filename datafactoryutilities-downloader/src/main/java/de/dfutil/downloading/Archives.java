package de.dfutil.downloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@EnableConfigurationProperties(DatafactoryDownloaderConfigurationProperties.class)
public class Archives {

    private static final Logger log = LoggerFactory.getLogger(Archives.class);

    private final DatafactoryDownloaderConfigurationProperties datafactoryDownloaderConfigurationProperties;

    public Archives(final DatafactoryDownloaderConfigurationProperties datafactoryDownloaderConfigurationProperties) {
        this.datafactoryDownloaderConfigurationProperties = datafactoryDownloaderConfigurationProperties;
    }

    private File newFileFromZipEntry(final File destinationDir, final ZipEntry zipEntry) throws IOException {
        final File destFile = new File(destinationDir, zipEntry.getName());

        final String destDirPath = destinationDir.getCanonicalPath();
        final String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: {}" + zipEntry.getName());
        }
        return destFile;
    }

    public void unzip() {
        final byte[] buffer = new byte[1024];
        try (final FileInputStream fis = new FileInputStream(this.datafactoryDownloaderConfigurationProperties.getArchivedTargetDestination());
             final ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry zipEntry = zis.getNextEntry();
            while (null != zipEntry) {
                final File newFile = this.newFileFromZipEntry(new File(this.datafactoryDownloaderConfigurationProperties.getTargetDestination()), zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    final File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }
                    final FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while (0 < (len = zis.read(buffer))) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (final IOException e) {
            Archives.log.error("Error during unzip operation\n{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}


