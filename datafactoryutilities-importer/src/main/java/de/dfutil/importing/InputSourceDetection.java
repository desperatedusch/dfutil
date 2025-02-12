package de.dfutil.importing;

import de.dfutil.dao.jpa.ImportResultRepository;
import de.dfutil.entities.jpa.ImportResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private List<ImportResult> alreadySuccessfulImports;

    @Autowired
    private ImportResultRepository importResultRepository;

    @Value("${app.importer.inputsource.folders}")
    @NonNull
    private String inputFolders;
    @Value("${app.importer.inputsource.filenamemask}")
    @NonNull
    private String inputFilenameMask;

    public InputSourceDetection() {

    }

    public List<Path> findFiles() throws IOException {
        var folders = inputSourceFolders();
        var result = new ArrayList<Path>();
        alreadySuccessfulImports = importResultRepository.findAllSuccessfulImportResults();
        for (var folder : folders) {
            List<Path> files = searchFilesIn(folder);
            result.addAll(files);
        }
        return result;
    }

    private List<Path> inputSourceFolders() {
        var folders2Scan = new ArrayList<Path>();
        final var stringTokenizer = new StringTokenizer(inputFolders, ";", false);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            folders2Scan.add(Paths.get(token));
        }
        log.info("Following folders are scanned for input sources:");
        folders2Scan.forEach(f -> log.info(f.toString()));
        return folders2Scan;
    }

    private List<Path> searchFilesIn(Path startPath) throws IOException {
        final var pattern = "glob:" + inputFilenameMask;
        final var pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
        final var paths = new ArrayList<Path>();
        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (attrs.isRegularFile()) {
                    Path fileName = file.getFileName();
                    if (pathMatcher.matches(fileName)) {
                        if (alreadySuccessfulImports.stream().anyMatch(ir -> ir.getFileName().equals(fileName.toString()))) {
                            log.info("File {} was already processed successfully", file.toFile().getAbsolutePath());
                            return FileVisitResult.CONTINUE;
                        }
                        log.info("File {} is detetected for processing", file.toFile().getAbsolutePath());
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
