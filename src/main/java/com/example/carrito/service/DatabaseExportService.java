package com.example.carrito.service; // <-- adjust if your structure differs

import com.example.carrito.model.Product;
import com.example.carrito.repository.ProductRepo; // <-- using your correct repo name
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class DatabaseExportService {

    @Autowired
    private ProductRepo productRepo; // <-- correct repo reference

    public Path exportDatabase() throws IOException {
        List<Product> products = productRepo.findAll();

        Path tempFile = Files.createTempFile("products-export-", ".sql");

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(tempFile))) {
            for (Product p : products) {
                writer.printf(
                    "INSERT INTO product (id, name, price) VALUES (%d, '%s', %.2f);%n",
                    p.getId(), p.getName(), p.getPrice()
                );
            }
        }

        return tempFile;
    }
}
