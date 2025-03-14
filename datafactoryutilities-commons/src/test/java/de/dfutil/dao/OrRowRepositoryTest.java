package de.dfutil.dao;

import de.dfutil.entities.OrRow;
import de.dfutil.entities.ids.OrRowId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrRowRepositoryTest {

    private OrRowRepository cut;

    @Test
    public void findValidById() {
        OrRow processableOr;
        cut.findById(
                new OrRowId(
                        "ORTSTEILNAME", "G"
                )
        );
    }

}
