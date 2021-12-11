package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.dto.BuyerSaveDTO;
import com.waa.AmazonMini.dto.BuyerUpdateDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.service.interfaces.IBuyerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer")
@CrossOrigin
//@PreAuthorize("hasRole('BUYER')") //TODO Uncomment once Auth service has been added
public class BuyerController {
    private final IBuyerService buyerService;

    public BuyerController(IBuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/{buyerId}")
    public Buyer findById(@PathVariable("buyerId") Long buyerId){
        return buyerService.findById(buyerId);
    }

    @GetMapping
    public List<Buyer> findAll(){
        return buyerService.findAll();
    }

    @GetMapping(params = " paged = true ")
    public Page<Buyer> findAllPageable(Pageable pageable){
        return buyerService.findAll(pageable);
    }

    @PostMapping
    //@PreAuthorize("hasRole('SELLER')") //TODO uncomment once Auth service has been added
    public ResponseMessage add(@RequestBody BuyerSaveDTO dto){
        return buyerService.save(dto);
    }

    @PutMapping
    //@PreAuthorize("hasRole('SELLER')")
    public ResponseMessage update(@RequestBody BuyerUpdateDTO dto) {
        return buyerService.update(dto);
    }

    @DeleteMapping("/{productId}")
    //@PreAuthorize("hasRole('SELLER')")
    public ResponseMessage delete(@PathVariable("productId") Long productId) {
        return buyerService.delete(productId);
    }
}