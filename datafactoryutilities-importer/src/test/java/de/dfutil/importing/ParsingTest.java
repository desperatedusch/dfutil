package de.dfutil.importing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;


@Import(TestConfig.class)
@SpringBootTest
public class ParsingTest {

    @Autowired
    private Parsing cut;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void parseInputFile() throws IOException {
        cut.fromFile(
                resourceLoader.getResource(
                        "D:/Stuff/Datafactory/2024/B2401001.DAT").getFile().toPath());
    }

}
