package de.dfutil.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

@Service
public class FileParser {

    private static final Logger log = LoggerFactory.getLogger(FileParser.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public FileParser() {}

    public void parseFileWithBufferedReader(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process each line
                log.trace(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void parseFileViaRandomAccessFile(String path, String charset) {
        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            String str;
            while ((str = file.readLine()) != null) {
                log.trace(str);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
