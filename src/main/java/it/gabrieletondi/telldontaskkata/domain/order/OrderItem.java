package it.gabrieletondi.telldontaskkata.domain.order;

import it.gabrieletondi.telldontaskkata.domain.Product;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTax(){
        return this.product.getUnitaryTax().multiply(BigDecimal.valueOf(this.quantity));
    }

    public BigDecimal getTaxedAmount(){
        return product.getUnitaryTaxAmount()
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, HALF_UP);
    }
}
