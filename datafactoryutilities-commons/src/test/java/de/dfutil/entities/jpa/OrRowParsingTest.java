package de.dfutil.entities.jpa;

import de.dfutil.entities.OrRow;
import org.junit.jupiter.api.Test;

public class OrRowParsingTest {

    private static final String HOPSTENBAUERSCHAFT =
            "OR23092441978010100000500SAa-Bauerschaft                          Hopsten                                                                Aa-Bauerschaft                  26631500$                                                                                                                                                  \n";

    @Test
    public void parseOrRowBytes2Object() {
        OrRow parsedOrRow = OrRow.parseFrom(HOPSTENBAUERSCHAFT);
        parsedOrRow.toString();
    }
}
