package de.dfutil.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

@TestConfiguration
public class DataJpaTestConfiguration {

    @Autowired
    private TestEntityManager entityManager;

    @Bean
    public OrRowRepository orRowRepository() {
        final RepositoryFactorySupport factory = new JpaRepositoryFactory(this.entityManager.getEntityManager());
        return factory.getRepository(OrRowRepository.class);
    }

}
