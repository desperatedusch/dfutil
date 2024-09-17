package de.dfutil.core.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;


@SpringBootTest
class DataFactoryFileParserTest {

    @Autowired
    private FileParser cut;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    @Disabled
    public void testParseDataFactoryFileSBRowWithFileChannel() throws IOException {
        cut.parseFileWithFileChannel(resourceLoader.getResource("classpath:files2Import/B2308213.DAT").getFile().getPath());
    }

    @Test
    public void testParseDataFactoryFileSBRowWithAsynchronousileChannel() throws IOException, InterruptedException {
        cut.parseFileWithAsynchronousFileChannelBlockingBehaviour(resourceLoader.getResource("classpath:files2Import/B2308213.DAT").getFile().getPath());
        Thread.sleep(1000000);
    }

    @Test
    @Disabled
    public void testParseDataFactoryFileSBRowWithCompletionHandler() throws IOException {
        cut.parseFileWithAsynchronousFileChannelCompletionHandler(resourceLoader.getResource("classpath:files2Import/B2308213.DAT").getFile().getPath());
    }

}
