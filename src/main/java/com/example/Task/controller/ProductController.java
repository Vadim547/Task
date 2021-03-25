package com.example.Task.controller;

import com.example.Task.Dto.ProductBulkDto;
import com.example.Task.Dto.ProductByCountryDto;
import com.example.Task.Dto.ProductDemandDto;
import com.example.Task.Dto.ProductDto;
import com.example.Task.model.Product;
import com.example.Task.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/details")
    public ResponseEntity<ProductDto> findById (@RequestParam Long product_id) {
        return productService.findById(product_id);
    }

    @GetMapping("/high_demand_products")
    public ResponseEntity<List<ProductDemandDto>> getDemandProducts() {
        return productService.getDemandProducts();

    }

    @GetMapping("/bulk_products")
    public ResponseEntity<List<ProductBulkDto>> getBulkProducts() {
        return productService.getBulkProducts();
    }

    @GetMapping("/number_of_products_in_year")
    public ResponseEntity<List<ProductByCountryDto>> getProductsInYear() {
        return productService.getByCountry();
    }
}
