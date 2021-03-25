package com.example.Task.service;

import com.example.Task.Dto.ProductBulkDto;
import com.example.Task.Dto.ProductByCountryDto;
import com.example.Task.Dto.ProductDemandDto;
import com.example.Task.Dto.ProductDto;
import com.example.Task.exception.EntityException;
import com.example.Task.model.Category;
import com.example.Task.model.Customer;
import com.example.Task.model.Product;
import com.example.Task.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;
    CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public ResponseEntity<ProductDto> addProduct(ProductDto productDto) {
        Optional<Category> category = categoryService.findById(productDto.getCategoryId());
        if (category.isEmpty()) throw new EntityException("Category with " + productDto.getCategoryId() + " not found");

        Product product = new Product(
                productDto.getName(),
                category.get(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getPhoto()
        );
        productRepository.save(product);

        productDto.setId(product.getId());

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> product = productRepository.findAll();
        List<ProductDto> productDtos =  product.stream()
                .map(p -> new ProductDto.Builder(p.getId(),p.getCategory().getId())
                        .setDescription(p.getDescription())
                        .setName(p.getName())
                        .setPrice(p.getPrice())
                        .setPhoto(p.getPhoto()).build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    public ResponseEntity<ProductDto> findById(Long id) {
      Optional<Product> product = productRepository.findById(id);
      if (product.isEmpty()) throw new EntityException("Product with " + id + " not found");

      ProductDto productDto = new ProductDto.Builder(product.get().getId(), product.get().getCategory().getId())
                .setDescription(product.get().getDescription())
                .setName(product.get().getName())
                .setPrice(product.get().getPrice())
                .setPhoto(product.get().getPhoto()).build();

      return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ResponseEntity<List<ProductDemandDto>> getDemandProducts() {
        List<ProductDemandDto> demandDtos = productRepository.findDemandProducts().stream()
                .map(ProductDemandDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(demandDtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductBulkDto>> getBulkProducts() {
        List<ProductBulkDto> bulkDtosDtos = productRepository.findBulkProducts().stream()
                .map(ProductBulkDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(bulkDtosDtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductByCountryDto>> getByCountry() {

        List<ProductByCountryDto> products = productRepository.findOrdersByCountry().stream()
                .map(ProductByCountryDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
