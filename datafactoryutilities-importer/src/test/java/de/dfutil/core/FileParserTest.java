package de.dfutil.core;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
@SpringBootTest
@ActiveProfiles({"jpa", "dev"})
public class FileParserTest {

    @Autowired
    private FileParser cut;

    @Autowired
    private ResourceLoader resourceLoader;


    @Test
    public void parseDataFactoryFileSBRow() throws IOException {
        cut.parseFileWithBufferedReader(resourceLoader.getResource("classpath:files2Import/Streetcode_Stand_2024-01/B2401001.DAT").getFile().getPath());
    }

}
