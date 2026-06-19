package com.example.carrito;

import com.example.carrito.model.Product;
import com.example.carrito.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarritoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarritoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductRepo productRepo) {
        return args -> {
        	// Crear productos de prueba
        	Product p1 = new Product();
        	p1.setName("Jersey");
        	p1.setPrice(9.99);
        	productRepo.save(p1);

        	Product p2 = new Product();
        	p2.setName("Polo");
        	p2.setPrice(13.50);
        	productRepo.save(p2);

        	Product p3 = new Product();
        	p3.setName("Camiseta");
        	p3.setPrice(7.25);
        	productRepo.save(p3);

        	Product p4 = new Product();
        	p4.setName("Jeans");
        	p4.setPrice(25.00);
        	productRepo.save(p4);

        	Product p5 = new Product();
        	p5.setName("Sudadera");
        	p5.setPrice(19.99);
        	productRepo.save(p5);

        	Product p6 = new Product();
        	p6.setName("Zapatos");
        	p6.setPrice(39.90);
        	productRepo.save(p6);

        	Product p7 = new Product();
        	p7.setName("Gorra");
        	p7.setPrice(5.99);
        	productRepo.save(p7);

        	Product p8 = new Product();
        	p8.setName("Calcetines");
        	p8.setPrice(3.50);
        	productRepo.save(p8);

        	Product p9 = new Product();
        	p9.setName("Chaqueta");
        	p9.setPrice(49.99);
        	productRepo.save(p9);

        	Product p10 = new Product();
        	p10.setName("Bufanda");
        	p10.setPrice(12.00);
        	productRepo.save(p10);


            // imprimir todos los productos
            productRepo.findAll().forEach(System.out::println);
        };
    }
}
