package org.grocerybooking;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootConfiguration
@SpringBootApplication
public class GroceryApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run( GroceryApplication.class, args );
        System.out.println( "XXXXX STARTED XXXXX" );

        }

    @Override
    public void run(String... args) throws Exception {

    }
}
