package com.buatss.application;

import com.buatss.repository.util.ProductionDataInitializer;
import com.buatss.ui.UserInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileNotFoundException;

@SpringBootApplication
@ComponentScan(basePackages = "com.buatss")
public class Application implements CommandLineRunner {
    @Autowired
    UserInterface ui;

    @Autowired
    ProductionDataInitializer initializer;

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        new SpringApplicationBuilder(Application.class).profiles("prod").run(args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        initializer.loadInitialData();
        ui.applicationLoop();
    }
}
