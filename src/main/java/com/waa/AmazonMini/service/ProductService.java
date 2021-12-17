package com.waa.AmazonMini.service;

import com.waa.AmazonMini.auth.repository.UserRepository;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.dto.ProductSaveDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.repository.ProductRepository;
import com.waa.AmazonMini.repository.SellerRepository;
import com.waa.AmazonMini.service.interfaces.IProductService;
import com.waa.AmazonMini.utils.LocalUtil;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.waa.AmazonMini.utils.constants.AnswerMessage.*;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerService sellerService;
    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseMessage save(ProductSaveDTO dto) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        var user = userRepository.findByUsername(userDetails.getUsername());
        var seller = sellerService.getSellerByUserId(user.get().getId());

        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPricePerUnit(), dto.getQuantity(), seller, null);
        Product savedProduct = productRepository.save(product);
        return new ResponseMessage(SUCCESSFUL_MESSAGE, HttpStatus.OK, savedProduct);
    }

    @Override
    public ResponseMessage update(ProductUpdateDTO dto) {
        Product fromDB = productRepository.getById(dto.getId());
        if (fromDB != null) {
            fromDB.setName(dto.getName());
            fromDB.setDescription(dto.getDescription());
            fromDB.setPricePerUnit(dto.getPricePerUnit());
            fromDB.setPhotoes(dto.getPhotoes());
            return new ResponseMessage(SUCCESSFUL_MESSAGE, HttpStatus.OK, LocalUtil.getMessage("productId", fromDB.getId()));
        } else {
            return new ResponseMessage(DATA_NOT_FOUND_TO_UPDATE, HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseMessage delete(Long id) {
        Product product = findById(id);
        if (product != null) {
            //if()  //TODO All sold products must be delivered successfully before Delete
            productRepository.delete(product);
            return new ResponseMessage("Successful", HttpStatus.OK, LocalUtil.getMessage("ProductID", product.getId()));
        }
        return new ResponseMessage(DATA_NOT_FOUND_TO_DELETE, HttpStatus.CONFLICT);
    }

    @Override
    public Page<Product> findAll(@PageableDefault(size = 50) Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
