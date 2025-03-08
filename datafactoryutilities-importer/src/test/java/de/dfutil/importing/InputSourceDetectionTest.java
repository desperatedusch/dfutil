package de.dfutil.importing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

@Import(TestConfig.class)
@SpringBootTest
@TestPropertySource(
        properties =
                """
                            app.importer.inputsource.folders=de/dfutil/importing/2024
                            app.importer.inputsource.filenamemask=B*.DAT
                        """)
public class InputSourceDetectionTest {

    @Autowired
    private InputSourceDetection cut;

    @Test
    public void findInputFiles() throws IOException {
        Assertions.assertEquals(10, cut.findFiles().size());
    }

}