package de.dfutil.dao;

import de.dfutil.entities.ids.OrRowId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest(properties = {
        "jdbc:h2:mem:mydatabase;MODE=PostgreSQL",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect"})
@Import(DataJpaTestConfiguration.class)
public class OrRowRepositoryTest {


    @Autowired
    private OrRowRepository cut;

    @Test
    public void findValidById() {
        Assertions.assertTrue(cut.findById(
                new OrRowId(
                        "ORTSTEILNAME", "G"
                )
        ).isEmpty());
    }

}
