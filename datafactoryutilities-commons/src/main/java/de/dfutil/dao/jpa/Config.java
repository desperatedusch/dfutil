package de.dfutil.dao.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile({"jpa", "!redis"})
@Configuration
public class Config {

}
