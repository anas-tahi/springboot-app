package com.example.carrito.service;

import com.example.carrito.model.Product;
import com.example.carrito.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword, Double minPrice, Double maxPrice, String category) {
        return productRepo.findAll().stream()
                .filter(p -> keyword == null || keyword.isEmpty() || p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .filter(p -> category == null || category.isEmpty() || category.equals("all") || 
                             (p.getCategory() != null && p.getCategory().equalsIgnoreCase(category)))
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return productRepo.findAll().stream()
                .map(Product::getCategory)
                .filter(c -> c != null && !c.isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public long countLowStock() {
        return productRepo.findAll().stream()
                .filter(p -> p.getStock() != null && p.getStock() < 5)
                .count();
    }
}
