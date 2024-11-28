package de.dfutil.core.files;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@Service
public class Watcher {

    public List<String> scanForInputFiles() {
        var paths = new ArrayList<>();
        var startPath = Paths.get("/pfad/zum/startordner");
        var pattern = "glob:*.txt"; // Beispiel: Suche nach allen .txt Dateien

        List<Path> result = null;
        try {
            result = searchFiles(startPath, pattern);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        throw new UnsupportedOperationException("Finish implementation...");

    }


    private List<Path> searchFiles(Path startPath, String pattern) throws IOException {
        final var pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
        final var result = new ArrayList<Path>();

        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (pathMatcher.matches(file.getFileName())) {
                    result.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.println("Fehler beim Zugriff auf Datei: " + file);
                return FileVisitResult.CONTINUE;
            }
        });

        return result;
    }
}
