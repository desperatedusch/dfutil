package de.dfutil.files;

import de.dfutil.importing.InputSourceDetection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
@SpringBootTest
@ActiveProfiles({"dev"})
@TestPropertySource(
        properties =
                """
                            app.importer.inputsource.folders=D:/Stuff/Datafactory/2024/
                            app.importer.inputsource.filenamemask=B*.DAT
                        """)
public class InputSourceDetectionTest {

    @Autowired
    private InputSourceDetection cut;

    @Test
    public void findInputFiles() throws IOException {
        List<Path> inputFiles = new ArrayList<>();
        inputFiles.addAll(cut.findFiles());
    }

}