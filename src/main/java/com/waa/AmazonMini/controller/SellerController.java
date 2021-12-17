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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/seller")
//@CrossOrigin
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("sign-up")
    public Seller SignupSeller(@RequestBody SellerSaveDTO dto){
        return sellerService.registerSeller(dto);
    }

    @GetMapping(params = "paged=true")
    public Page<Seller> findAllPageable(org.springframework.data.domain.Pageable pageable){
        return sellerService.findAll(pageable);
    }

    @PutMapping("/{id}/approve")
    public ResponseMessage approveSeller(@PathVariable(value = "id") long Id) {
        var res = new ResponseMessage(sellerService.ApproveSeller(Id), HttpStatus.OK);
        return res;
    }

    @PutMapping("/{id}/reject")
    public ResponseMessage rejectSeller(@PathVariable(value = "id") long Id) {
        var res = new ResponseMessage(sellerService.RejectSeller(Id), HttpStatus.OK);
        return res;
    }

    @GetMapping("/{id}/profile")
    public ResponseMessage getProfile(@PathVariable(value = "id") long Id) {
        return new ResponseMessage("Seller Info", HttpStatus.OK, sellerService.getSeller(Id));
    }

    @GetMapping("/unapprovedSellers")
    public List<Seller> getUnApprovedSeller() {
        return sellerService.getUnApprovedSeller();
    }





}
