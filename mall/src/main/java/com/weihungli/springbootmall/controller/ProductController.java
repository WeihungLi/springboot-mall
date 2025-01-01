package com.weihungli.springbootmall.controller;


import com.weihungli.springbootmall.constant.ProductCategory;
import com.weihungli.springbootmall.dto.ProductQueryParams;
import com.weihungli.springbootmall.dto.ProductRequest;
import com.weihungli.springbootmall.model.Product;
import com.weihungli.springbootmall.service.ProductService;
import com.weihungli.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(required = false) ProductCategory category,
                                                     @RequestParam(required = false) String search,
                                                     @RequestParam(defaultValue = "created_date") String orderBy,
                                                     @RequestParam(defaultValue = "desc") String sort,
                                                     @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                                                     @RequestParam(defaultValue = "0") @Min(0) Integer offset)
    {
        ProductQueryParams params = new ProductQueryParams();
        params.setCategory(category);
        params.setSearch(search);
        params.setOrderBy(orderBy);
        params.setSort(sort);
        params.setLimit(limit);
        params.setOffset(offset);
        List<Product> productList = productService.getProducts(params);

        Integer productTotal = productService.getTotal(params);

        Page<Product> page = new Page<>();
        page.setTotal(productTotal);
        page.setResult(productList);
        page.setLimit(limit);
        page.setOffset(offset);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }



    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        Product product = productService.getProductById(productId);

        System.out.println();
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        System.out.println("Start Create Product");
        Integer productId = productService.createProduct(productRequest);
        System.out.println("Start Create 22222");
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {
        Product product = productService.getProductById(productId);
        if (product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId,productRequest);

        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
