package com.example.carrito.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getSubtotal() { return product.getPrice() * quantity; }
    public void increment() { this.quantity++; }
    public void decrement() { if (this.quantity > 0) this.quantity--; }
}
