package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.dto.BuyerSaveDTO;
import com.waa.AmazonMini.dto.BuyerUpdateDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.dto.SellerSaveDTO;
import com.waa.AmazonMini.service.interfaces.IBuyerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer")
@CrossOrigin
//@PreAuthorize("hasRole('BUYER')") //TODO Uncomment once Auth service has been added
public class BuyerController {
    @Autowired
    private IBuyerService buyerService;

//    public BuyerController(IBuyerService buyerService) {
//        this.buyerService = buyerService;
//    }


    @PostMapping("sign-up")
    public Buyer SignupBuyer(@RequestBody BuyerSaveDTO dto){
        return buyerService.registerBuyer(dto);
    }


    @GetMapping("/{buyerId}")
    public Buyer findById(@PathVariable("buyerId") Long buyerId){
        return buyerService.findById(buyerId);
    }


    @GetMapping(params = " paged = true ")
    public Page<Buyer> findAllPageable(Pageable pageable){
        return buyerService.findAll(pageable);
    }

    @PutMapping
    public ResponseMessage update(@RequestBody BuyerUpdateDTO dto) {
        return buyerService.update(dto);
    }

    @DeleteMapping("/{buyerId}")
    public ResponseMessage delete(@PathVariable("buyerId") Long buyerId) {
        return buyerService.delete(buyerId);
    }


    @PutMapping("/follow/{sellerId}")
    public ResponseMessage follow(@PathVariable("sellerId") Long sellerId){
        return buyerService.followSeller(sellerId);
    }

    @PutMapping("/unfollow/{sellerId}")
    public ResponseMessage unfollow(@PathVariable("sellerId") Long sellerId){
        return buyerService.unfollowSeller(sellerId);
    }

}
