package com.kirasin.mapper.product;

import com.kirasin.dto.product.ProductCreateEditDto;
import com.kirasin.mapper.Mapper;
import com.kirasin.model.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDto, Product> {
    @Override
    public Product map(ProductCreateEditDto object) {
        Product product = new Product();
        copy(object, product);
        return product;
    }

    @Override
    public Product map(ProductCreateEditDto fromObject, Product toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ProductCreateEditDto object, Product product) {
        product.setProductName(object.getNewProductName());
        product.setDescription(object.getNewDescription());
        product.setPrice(object.getNewPrice());
        product.setQuantity(object.getNewQuantity());
    }
}
