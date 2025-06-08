package de.dfutil.importing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestConfig.class)
@SpringBootTest
public class SuccessionHandlingTest {

    @Autowired
    private SuccessionsUpdater cut;

}


