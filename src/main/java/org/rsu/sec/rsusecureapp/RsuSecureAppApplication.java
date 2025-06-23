package org.rsu.sec.rsusecureapp;

import org.rsu.sec.rsusecureapp.entities.Product;
import org.rsu.sec.rsusecureapp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class RsuSecureAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsuSecureAppApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("computer").price(2300).quantity(12).build());
            productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Printer").price(1200).quantity(10).build());
            productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Smart Phone").price(4200).quantity(34).build());
        };
}
}
