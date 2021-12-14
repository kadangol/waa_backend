package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.dto.SellerSaveDTO;
import com.waa.AmazonMini.service.OrderLineService;
import com.waa.AmazonMini.service.SellerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import lombok.var;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/seller")
//@CrossOrigin
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("sign-up")
    public ResponseMessage SignupSeller(@RequestBody SellerSaveDTO dto) {
        var result = new ResponseMessage("Seller created.", HttpStatus.OK);
        result.setData(sellerService.registerSeller(dto));
        return result;
    }


//    @GetMapping
//    public ResponseMessage getSeller(Pageable pageable) {
//        var res = new ResponseMessage("Seller List", HttpStatus.OK);
//        res.setData(sellerService.findAll(pageable));
//        return res;
//    }

    @GetMapping(params = " paged = true ")
    public Page<Seller> findAllPageable(org.springframework.data.domain.Pageable pageable){
        return sellerService.findAll(pageable);
    }

    @PutMapping("/approve/{id}")
    public ResponseMessage approveSeller(@PathVariable(value = "id") long Id) {
        var res = new ResponseMessage(sellerService.ApproveSeller(Id), HttpStatus.OK);
        return res;

    }

    @PutMapping("/reject/{id}")
    public ResponseMessage rejectSeller(@PathVariable(value = "id") long Id) {
        var res = new ResponseMessage(sellerService.ApproveSeller(Id), HttpStatus.OK);
        return res;

    }

}
