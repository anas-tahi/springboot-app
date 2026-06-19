package com.example.carrito.service;

import com.example.carrito.model.CartItem;
import com.example.carrito.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final Map<Integer, CartItem> cartItems = new LinkedHashMap<>();

    public Collection<CartItem> getCartItems() {
        return cartItems.values();
    }

    public void addProduct(Product product) {
        if (cartItems.containsKey(product.getId())) {
            cartItems.get(product.getId()).increment();
        } else {
            cartItems.put(product.getId(), new CartItem(product));
        }
    }

    public void removeProduct(Integer productId) {
        cartItems.remove(productId);
    }

    public void decrementProduct(Integer productId) {
        if (cartItems.containsKey(productId)) {
            CartItem item = cartItems.get(productId);
            if (item.getQuantity() <= 1) {
                cartItems.remove(productId);
            } else {
                item.decrement();
            }
        }
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double getTotal() {
        return cartItems.values().stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public int getCartCount() {
        return cartItems.values().stream().mapToInt(CartItem::getQuantity).sum();
    }
}
