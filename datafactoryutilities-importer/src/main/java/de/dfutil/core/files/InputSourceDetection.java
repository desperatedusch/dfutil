package de.dfutil.core.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class InputSourceDetection {

    private static final Logger log = LoggerFactory.getLogger(InputSourceDetection.class);

    @Value("${app.importer.inputsource.folders}")
    @NonNull
    private String inputFolders;
    @Value("${app.importer.inputsource.filenamemask}")
    @NonNull
    private String inputFilenameMask;

    public InputSourceDetection() {
    }

    public List<Path> inputSourceFolders() {
        var folders2Scan = new ArrayList<Path>();
        var stringTokenizer = new StringTokenizer(inputFolders, ";", false);
        while (stringTokenizer.hasMoreTokens()) {
            folders2Scan.add(Paths.get(stringTokenizer.nextToken()));
        }
        return folders2Scan;
    }

    public List<Path> findInputFiles(Path root) {
        var pattern = "glob:" + inputFilenameMask;
        try {
            return searchFiles(root, pattern);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private List<Path> searchFiles(Path startPath, String pattern) throws IOException {
        final var pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
        final var paths = new ArrayList<Path>();

        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (attrs.isRegularFile()) {
                    if (pathMatcher.matches(file.getFileName())) {
                        paths.add(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });
        return paths;
    }
}
