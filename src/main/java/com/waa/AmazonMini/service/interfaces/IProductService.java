package com.waa.AmazonMini.service.interfaces;

import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.dto.ProductSaveDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IProductService {
    ResponseMessage save(ProductSaveDTO productSaveDTO);

    ResponseMessage update(ProductUpdateDTO productUpdateDTO);

    ResponseMessage delete(Long id);

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    List<Product> findAll();
}
