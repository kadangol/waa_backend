package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.dto.SellerSaveDTO;
import com.waa.AmazonMini.service.OrderLineService;
import com.waa.AmazonMini.service.SellerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
//@CrossOrigin
public class SellerController {

    private SellerService sellerService;

    @PostMapping("sign-up")
    public Seller SignupSeller(@RequestBody SellerSaveDTO dto){
        sellerService.registerSeller(dto);
        return sellerService.registerSeller(dto);
    }


    @GetMapping
    public ResponseMessage getSeller(){
        return new ResponseMessage();
    }

}
