
package com.hq.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class ApplicationStartUp {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartUp.class, args);
    }

}
