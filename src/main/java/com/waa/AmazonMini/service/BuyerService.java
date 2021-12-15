package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.dto.BuyerSaveDTO;
import com.waa.AmazonMini.dto.BuyerUpdateDTO;
import com.waa.AmazonMini.repository.BuyerRepository;
import com.waa.AmazonMini.service.interfaces.IBuyerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Struct;
import java.util.List;

@Service
@Transactional
public class BuyerService implements IBuyerService {

    @Autowired
    private BuyerRepository buyerRepository;


    @Override
    public ResponseMessage update(BuyerUpdateDTO buyerUpdateDTO) {
        return null;
    }

    @Override
    public ResponseMessage delete(Long id) {
        var buyer = buyerRepository.findById(id);
        if (buyer.isPresent()) {
            buyer.get().getUser().setIsDeleted(1);
        }
       return  new ResponseMessage("Buyer Deleted", HttpStatus.OK);
    }


    @Override
    public Buyer findById(Long id) {
        var buyer = buyerRepository.findById(id);
        if (buyer.isPresent())
            return buyer.get();
        else
            return new Buyer();
    }

    @Override
    public Page<Buyer> findAll(@PageableDefault(size = 50) Pageable pageable) {
        return buyerRepository.findAll(pageable);
    }


}
