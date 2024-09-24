package de.dfutil.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

@Service
public class FileParser {

    private static final Logger log = LoggerFactory.getLogger(FileParser.class);

    private final ApplicationEventPublisher eventPublisher;

    public FileParser(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void parseFileWithFileChannel(String path) {
        try {
            RandomAccessFile file = new RandomAccessFile(path, "r");
            FileChannel channel = file.getChannel();
            log.info("File size is {}: ", channel.size());
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            buffer.flip();//Restore buffer to position 0 to read it
            log.info("Reading content and printing ... ");
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < channel.size(); i++) {
                stringBuffer.append((char) buffer.get());
            }
            log.info(stringBuffer.toString());
            channel.close();
            file.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void parseFileWithAsynchronousFileChannelBlockingBehaviour(String filePath) {
        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(324);
            long position = 0;
            Future<Integer> result = fileChannel.read(buffer, position);
            while (!result.isDone()) {
                position += result.get();
                String line = StandardCharsets.UTF_8.decode(buffer).toString();
                log.info("Parsed row: {}", line);
                buffer.flip();
            }
            buffer.clear();
            log.info("parseFileWithAsynchronousFileChannelBlockingBehaviour finished");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void parseFileWithAsynchronousFileChannelCompletionHandler(String filePath) {
        try {
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(324);
            long position = 0;

            fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                    public void completed(Integer result, ByteBuffer attachment) {
                        log.info("CompletionResult = {}", result);
                        attachment.flip();
                        byte[] data = new byte[attachment.limit()];
                        attachment.get(data);
                        log.info("Hier kommen die Zeilenweise rein: {}", String.valueOf(data));
                        attachment.clear();
                    }

                    @Override
                    public void failed(Throwable t, ByteBuffer attachment) {
                        log.error(t.getMessage());
                    }
                });
            } catch(IOException e){
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }


}
