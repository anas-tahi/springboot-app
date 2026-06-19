package com.example.carrito.controller;

import com.example.carrito.model.Product;
import com.example.carrito.service.CartService;
import com.example.carrito.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getTotal());
        model.addAttribute("cartCount", cartService.getCartCount());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            cartService.addProduct(product);
        }
        return "redirect:/products";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Integer id) {
        cartService.removeProduct(id);
        return "redirect:/cart";
    }

    @PostMapping("/decrement/{id}")
    public String decrementFromCart(@PathVariable("id") Integer id) {
        cartService.decrementProduct(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
