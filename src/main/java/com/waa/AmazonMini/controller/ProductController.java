package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.dto.ProductSaveDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.service.interfaces.IProductService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
//    private final IProductService productService;
//
//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }
//
//    @GetMapping("/{productId}")
//    public Product findById(@PathVariable("productId") Long productId){
//        return productService.findById(productId);
//    }
//
//    @GetMapping
//    public List<Product> findAll(){
//        return productService.findAll();
//    }
//
//    @GetMapping(params = " paged = true ")
//    public Page<Product> findAllPageable(Pageable pageable){
//        return productService.findAll(pageable);
//    }
//
//    @PostMapping
//    //@PreAuthorize("hasRole('SELLER')") //TODO uncomment once Auth service has been added
//    public ResponseMessage add(@RequestBody ProductSaveDTO dto){
//        return productService.save(dto);
//    }
//
//    @PutMapping
//    //@PreAuthorize("hasRole('SELLER')")
//    public ResponseMessage update(@RequestBody ProductUpdateDTO dto) {
//            return productService.update(dto);
//    }
//
//    @DeleteMapping("/{productId}")
//    //@PreAuthorize("hasRole('SELLER')")
//    public ResponseMessage delete(@PathVariable("productId") Long productId) {
//        return productService.delete(productId);
//    }
}
