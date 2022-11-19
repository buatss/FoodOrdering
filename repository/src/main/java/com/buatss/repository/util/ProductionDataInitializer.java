package com.buatss.repository.util;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;

@Component
public class ProductionDataInitializer {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void loadInitialData() throws FileNotFoundException {
        final File initialFile = new File("repository/src/main/resources/prodData.sql");
        final InputStream targetStream = new DataInputStream(new FileInputStream(initialFile));
        new BufferedReader(new InputStreamReader(targetStream)).lines()
                                                               .filter(line -> line.startsWith("INSERT INTO"))
                                                               .forEach(query -> entityManager.createNativeQuery(query)
                                                                                              .executeUpdate());

    }
}
