package com.ponlanby.jpgmigrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(value = "com.ponlanby.jpgmigrator")
public class JpgMigratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpgMigratorApplication.class, args);
    }

}
