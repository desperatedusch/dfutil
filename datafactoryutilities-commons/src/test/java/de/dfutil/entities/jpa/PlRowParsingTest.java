package de.dfutil.entities.jpa;

import org.junit.jupiter.api.Test;

public class PlRowParsingTest {

    private static final String HAMBURGERPLZ =
            "PL23082132007031220639213365000522Hamburg                                                                Hamburg                 N686026222133650002000000      0  2120$                                                                                                                                                            \n";

    @Test
    public void parsePlRowBytes2Object() {
        PlRow parsedPlRow = PlRow.parseFrom(HAMBURGERPLZ);
        parsedPlRow.toString();
    }
}
