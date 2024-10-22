package de.dfutil.core.services;

import de.dfutil.core.FileParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@Import(DataFactoryFileParserTestConfiguration.class)
@SpringBootTest
class DataFactoryFileParserTest {

    @Autowired
    private FileParser cut;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void testParseDataFactoryFileSBRow() throws IOException {
        cut.parseFileWithBufferedReader(resourceLoader.getResource("classpath:files2Import/B2308213.DAT").getFile().getPath());
    }

    @Test
    public void testParseDataFactoryFileSBRowViaRandomAccessFile() throws IOException {
        cut.parseFileViaRandomAccessFile(resourceLoader.getResource("classpath:files2Import/B2308213.DAT").getFile().getPath(), "UTF-8");
    }

}
