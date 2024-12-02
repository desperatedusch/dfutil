package de.dfutil.core.files;

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
                            app.importer.inputsource.folders=D:/ideaWorkspaces/dfutil/datafactoryutilities-importer/src/test/resources/files2Import/Streetcode_Stand_2024-01;D:/ideaWorkspaces/dfutil/datafactoryutilities-importer/src/test/resources/files2Import/Streetcode_Stand_2024-10
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