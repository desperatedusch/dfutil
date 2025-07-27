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
@EnableConfigurationProperties(InputSourceConfigurationProperties.class)
public class InputSourceDetection {

    private static final Logger log = LoggerFactory.getLogger(InputSourceDetection.class);

    private final InputSourceConfigurationProperties inputSourceConfigurationProperties;

    private final ImportResultRepository importResultRepository;
    private List<ImportResultEntity> alreadySuccessfulImported;

    public InputSourceDetection(InputSourceConfigurationProperties inputSourceConfigurationProperties, ImportResultRepository importResultRepository) {
        this.inputSourceConfigurationProperties = inputSourceConfigurationProperties;
        this.importResultRepository = importResultRepository;
    }

    @PostConstruct
    public void postConstruct() {
        alreadySuccessfulImported =
                importResultRepository
                        .findAll()
                        .stream()
                        .filter(ImportResultEntity::isImportSuccessful)
                        .toList();
    }

    public List<Path> findFiles() throws IOException {
        var folders = inputSourceFolders();
        var result = new ArrayList<Path>();
        for (var folder : folders) {
            List<Path> files = searchFilesIn(folder);
            result.addAll(files);
        }
        return result;
    }

    private List<Path> inputSourceFolders() {
        var folders2Scan = new ArrayList<Path>();
        final var stringTokenizer = new StringTokenizer(inputSourceConfigurationProperties.getInputFolders(), ";", false);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            folders2Scan.add(Paths.get(token));
        }
        log.info("Following folders are scanned for input sources:");
        folders2Scan.forEach(f -> log.info(f.toString()));
        return folders2Scan;
    }

    private List<Path> searchFilesIn(Path startPath) throws IOException {
        final var pattern = "glob:" + inputSourceConfigurationProperties.getMainFileName();
        final var pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
        final var paths = new ArrayList<Path>();
        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (attrs.isRegularFile()) {
                    Path fileName = file.getFileName();
                    if (pathMatcher.matches(fileName)) {
                        if (alreadySuccessfulImported.stream().anyMatch(ir -> ir.getFileName().equals(fileName.toString()))) {
                            log.info("{} was already processed successfully", fileName);
                            return FileVisitResult.CONTINUE;
                        }
                        log.info("{} is detetected for processing", file.toFile().getAbsolutePath());
                        paths.add(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                log.error("Visiting file failed...", exc);
                return FileVisitResult.CONTINUE;
            }
        });
        return paths;
    }

}
