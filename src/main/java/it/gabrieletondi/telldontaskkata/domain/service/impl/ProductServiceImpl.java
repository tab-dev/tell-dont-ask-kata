package it.gabrieletondi.telldontaskkata.domain.service.impl;

import it.gabrieletondi.telldontaskkata.domain.exception.UnknownProductException;
import it.gabrieletondi.telldontaskkata.domain.model.Product;
import it.gabrieletondi.telldontaskkata.domain.service.ProductService;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductCatalog productCatalog;


    public ProductServiceImpl(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public Product getProduct(String name) {
        return Optional.ofNullable(productCatalog.getByName(name))
                .orElseThrow(() -> new UnknownProductException());
    }
}
