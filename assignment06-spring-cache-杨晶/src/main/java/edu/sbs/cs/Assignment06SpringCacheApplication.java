package edu.sbs.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Assignment06SpringCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Assignment06SpringCacheApplication.class, args);
    }

}
