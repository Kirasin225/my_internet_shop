package com.kirasin.service.impl;

import com.kirasin.dto.product.ProductCreateEditDto;
import com.kirasin.dto.product.ProductReadDto;
import com.kirasin.mapper.product.ProductCreateEditMapper;
import com.kirasin.mapper.product.ProductReadMapper;
import com.kirasin.repository.ProductRepository;
import com.kirasin.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductReadMapper readMapper;
    private final ProductCreateEditMapper createEditMapper;

    @Override
    public List<ProductReadDto> findAll() {
        return repository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    @Override
    public ProductReadDto addProduct(ProductCreateEditDto product) {
        return Optional.of(product)
                .map(createEditMapper::map)
                .map(repository::save)
                .map(readMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<ProductReadDto> findById(Long productId){
        return repository.findById(productId)
                .map(readMapper::map);
    }

    @Transactional
    @Override
    public Optional<ProductReadDto> updateProduct(Long productId, ProductCreateEditDto product) {
        return repository.findById(productId)
                .map(entity -> {
                    if (product.getNewProductName() != null) {
                        entity.setProductName(product.getNewProductName());
                    }
                    if (product.getNewDescription() != null) {
                        entity.setDescription(product.getNewDescription());
                    }
                    if (product.getNewPrice() != null) {
                        entity.setPrice(product.getNewPrice());
                    }
                    if (product.getNewQuantity() != null) {
                        entity.setQuantity(product.getNewQuantity());
                    }
                    return entity;
                })
                .map(repository::saveAndFlush)
                .map(readMapper::map);
    }

    @Override
    @Transactional
    public boolean deleteProductById(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return true;
                    }
                ).orElse(false);

    }
    public void decreaseProductQuantity(Long productId, Integer quantity) {
        var product = repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough product in stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);
    }
}
