package com.od.redis.service.impl;

import com.od.redis.model.Product;
import com.od.redis.repository.ProductRepository;
import com.od.redis.service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  @Cacheable(value = "products", key = "#id")
  public Optional<Product> getProductById(final String id) {
    return productRepository.findById(id);
  }

  @Override
  @CachePut(value = "products", key = "#product.id")
  public Product createProduct(final Product product) {
    return productRepository.save(product);
  }

  @Override
  @CachePut(value = "products", key = "#id")
  public Product updateProduct(final String id, final Product productDetails) {
    return productRepository.findById(id).map(product -> {
      Product newProduct = Product.builder()
          .id(productDetails.getId())
          .name(productDetails.getName())
          .price(productDetails.getPrice())
          .category(productDetails.getCategory())
          .description(productDetails.getDescription())
          .stock(productDetails.getStock())
          .build();
      return productRepository.save(newProduct);
    }).orElseGet(() -> {
      productDetails.setId(id);
      return productRepository.save(productDetails);
    });
  }

  @Override
  @CacheEvict(value = "products", key = "#id")
  public void deleteProduct(final String id) {
    productRepository.deleteById(id);
  }
}
