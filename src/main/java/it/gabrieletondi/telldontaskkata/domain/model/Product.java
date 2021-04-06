package it.gabrieletondi.telldontaskkata.domain.model;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private String name;
    private BigDecimal price;
    private Category category;

    public Product(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getUnitaryTax() {
        return this.price.divide(valueOf(100))
                .multiply(this.category.getTaxPercentage())
                .setScale(2, HALF_UP);
    }

    public BigDecimal getUnitaryTaxAmount() {
        return price.add(getUnitaryTax()).setScale(2, HALF_UP);
    }
}
