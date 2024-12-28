package de.dfutil.entities.jpa;

import org.junit.jupiter.api.Test;

public class OrRowParsingTest {

    private static final byte[] HOPSTENBAUERSCHAFT = "OR23092441978010100000500SAa-Bauerschaft                          Hopsten                                                                Aa-Bauerschaft                  26631500$                                                                                                                                                  \n".getBytes();

    @Test
    public void parseOrRowBytes2Object() {
        OrRow parsedOrRow = new OrRow().parseFrom(HOPSTENBAUERSCHAFT);
        parsedOrRow.toString();

    }

}
