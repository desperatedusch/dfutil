package de.dfutil.entities.jpa;

import org.junit.jupiter.api.Test;

public class KgRowBytes2JpaEntityTest {

    private static final byte[] FLENSBURG_STADT = "KG23092442023073101001000KFlensburg, Stadt                        $                                                                                                                                                                                                                                                                 \n".getBytes();

    @Test
    public void parseKgRowBytes2Object() {
        KgRow parsedKgRow = new KgRow().parseFrom(FLENSBURG_STADT);
        parsedKgRow.toString();

    }

}
