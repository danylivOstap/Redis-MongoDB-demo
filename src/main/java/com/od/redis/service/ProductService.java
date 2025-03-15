package com.od.redis.service;

import com.od.redis.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
  public List<Product> getAllProducts();
  public Optional<Product> getProductById(String id);

  public Product createProduct(Product product);

  public Product updateProduct(String id, Product productDetails);

  public void deleteProduct(String id);
}
