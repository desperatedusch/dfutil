package de.dfutil.entities.jpa;

import de.dfutil.entities.KgRow;
import org.junit.jupiter.api.Test;

public class KgRowParsingTest {

    private static final String FLENSBURG_STADT =
            "KG23092442023073101001000KFlensburg, Stadt                        $                                                                                                                                                                                                                                                                 \n";

    @Test
    public void parseKgRowBytes2Object() {
        KgRow parsedKgRow = KgRow.parseFrom(FLENSBURG_STADT);
        parsedKgRow.toString();
    }
}
