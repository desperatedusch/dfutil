package de.dfutil.core;

import org.h2.tools.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@Import(FileParserTestConfiguration.class)
@SpringBootTest
public class FileParserTest {

    @Autowired
    private FileParser cut;

    @Autowired
    private ResourceLoader resourceLoader;


    //@Disabled
    @Test
    public void parseDataFactoryFileSBRow() throws IOException {
        cut.parseFileWithBufferedReader(resourceLoader.getResource("classpath:files2Import/Streetcode_Stand_2024-01/B2401001.DAT").getFile().getPath());
    }

}

@TestConfiguration
class FileParserTestConfiguration {

    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

}
