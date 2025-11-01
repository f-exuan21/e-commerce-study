package kr.hhplus.be.server.ecommerce.domain.model;

public class Product {

    private final Long id;
    private final String name;
    private final Money price;
    private final int stock;

    public Product(Long id, String name, Money price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
