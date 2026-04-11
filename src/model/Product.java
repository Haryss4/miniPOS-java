package model;

/**
 * Model untuk produk yang dijual di POS.
 */
public class Product {

    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    public String getName()     { return name; }
    public double getPrice()    { return price; }
    public int getQuantity()    { return quantity; }

    @Override
    public String toString() {
        return String.format("  %-20s x%d  @ Rp %,.0f  = Rp %,.0f",
                name, quantity, price, getSubtotal());
    }
}