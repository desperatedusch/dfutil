package de.dfutil.dao;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@AutoConfiguration
@EnableTransactionManagement
public class TransactionalPersistenceConfig {

    @Bean
    public JpaTransactionManager transactionManager(
            final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}