package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SellerService sellerService;


//    @GetMapping("/approvedSellers")
//    public List<Seller> getApprovedSeller() {
//        return sellerService.getApprovedSeller();
//    }
//
}
