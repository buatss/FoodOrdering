package com.buatss.repository.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevDataSource {
    @Bean(name = "dataSource")
    DataSource dataSource() {
        return DevDataSource.testDB.getEmbeddedDatabase();
    }

    private static class testDB {
        private static EmbeddedDatabase embeddedDatabase;

        private static void buildDB() {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            builder.setType(EmbeddedDatabaseType.H2);
            embeddedDatabase = builder.build();
        }

        private static EmbeddedDatabase getEmbeddedDatabase() {
            if (embeddedDatabase == null) {
                buildDB();
            }
            return embeddedDatabase;
        }
    }

}
