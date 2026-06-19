package com.example.carrito.controller;

import com.example.carrito.model.Product;
import com.example.carrito.service.CartService;
import com.example.carrito.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping
    public String listProducts(
            @RequestParam(value = "q", required = false) String keyword,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "category", required = false) String category,
            Model model) {

        model.addAttribute("products", productService.searchProducts(keyword, minPrice, maxPrice, category));
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("q", keyword);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("cartCount", cartService.getCartCount());
        model.addAttribute("lowStockCount", productService.countLowStock());
        return "productos";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("cartCount", cartService.getCartCount());
        return "formulario-producto";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("cartCount", cartService.getCartCount());
        return "formulario-producto";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
