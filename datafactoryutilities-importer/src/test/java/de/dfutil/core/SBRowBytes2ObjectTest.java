package de.dfutil.core;

import de.dfutil.entities.SBRow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(SBRowBytes2ObjectTestConfiguration.class)
@SpringBootTest
public class SBRowBytes2ObjectTest {

    private static final byte[] ALPENSTRASSE = "SB2410277199910270000210000176200013                G01ALPENSTR                                      Alpenstr.                                     Alpenstr.             0N78267001   1318807008335001                                   $                                                                                          \n".getBytes();

    @Test
    public void parseSBRowBytes2Object() {
        SBRow expectedSBRow = new SBRow().parseFrom(ALPENSTRASSE);
        expectedSBRow.toString();
    }

}
