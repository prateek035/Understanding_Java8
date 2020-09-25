package com.shop;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Shop {

    private List<Product> products;
    private String name;

    public Shop(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Optional<Integer> getProductPrice(String product) {
        Optional<Product> requiredProduct = products.stream()
                                    .filter(prod -> prod.getName().equals(product)).findFirst();

        Optional<Integer> requiredProductPrice = Optional.empty();

        return requiredProduct.map(Product::getPrice).or(() -> requiredProductPrice);
    }
}


