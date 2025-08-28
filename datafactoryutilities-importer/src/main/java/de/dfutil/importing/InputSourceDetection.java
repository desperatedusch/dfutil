package de.dfutil.importing;

import de.dfutil.dao.ImportResultRepository;
import de.dfutil.entities.ImportResultEntity;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@EnableConfigurationProperties(ImporterConfigurationProperties.class)
public class InputSourceDetection {

    private static final Logger log = LoggerFactory.getLogger(InputSourceDetection.class);

    private final ImporterConfigurationProperties importerConfigurationProperties;

    private final ImportResultRepository importResultRepository;
    private List<ImportResultEntity> alreadySuccessfulImported;

    public InputSourceDetection(final ImporterConfigurationProperties importerConfigurationProperties, final ImportResultRepository importResultRepository) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.importResultRepository = importResultRepository;
    }

    @PostConstruct
    public void postConstruct() {
        this.alreadySuccessfulImported =
                this.importResultRepository
                        .findAll()
                        .stream()
                        .filter(ImportResultEntity::isImportSuccessful)
                        .toList();
    }

    public List<Path> findFiles() throws IOException {
        final var folders = this.inputSourceFolders();
        final var result = new ArrayList<Path>();
        for (final var folder : folders) {
            final List<Path> files = this.searchFilesIn(folder);
            result.addAll(files);
        }
        return result;
    }

    private List<Path> inputSourceFolders() {
        final var folders2Scan = new ArrayList<Path>();
        var stringTokenizer = new StringTokenizer(this.importerConfigurationProperties.getInputFolders(), ";", false);
        while (stringTokenizer.hasMoreTokens()) {
            final String token = stringTokenizer.nextToken();
            folders2Scan.add(Paths.get(token));
        }
        InputSourceDetection.log.info("Following folders are scanned for input sources:");
        folders2Scan.forEach(f -> InputSourceDetection.log.info(f.toString()));
        return folders2Scan;
    }

    private List<Path> searchFilesIn(final Path startPath) throws IOException {
        var pattern = "glob:" + this.importerConfigurationProperties.getMainFileName();
        var pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
        var paths = new ArrayList<Path>();
        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) {
                if (attrs.isRegularFile()) {
                    final Path fileName = file.getFileName();
                    if (pathMatcher.matches(fileName)) {
                        if (InputSourceDetection.this.alreadySuccessfulImported.stream().anyMatch(ir -> ir.getFileName().equals(fileName.toString()))) {
                            InputSourceDetection.log.info("{} was already processed successfully", fileName);
                            return FileVisitResult.CONTINUE;
                        }
                        InputSourceDetection.log.info("{} is detetected for processing", file.toFile().getAbsolutePath());
                        paths.add(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(final Path file, final IOException exc) {
                InputSourceDetection.log.error("Visiting file failed...", exc);
                return FileVisitResult.CONTINUE;
            }
        });
        return paths;
    }

}
