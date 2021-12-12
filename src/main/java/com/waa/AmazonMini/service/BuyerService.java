package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.dto.BuyerSaveDTO;
import com.waa.AmazonMini.dto.BuyerUpdateDTO;
import com.waa.AmazonMini.service.interfaces.IBuyerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BuyerService implements IBuyerService {
    @Override
    public ResponseMessage save(BuyerSaveDTO productSaveDTO) {
        return null;
    }

    @Override
    public ResponseMessage update(BuyerUpdateDTO productUpdateDTO) {
        return null;
    }

    @Override
    public ResponseMessage delete(Long id) {
        return null;
    }

    @Override
    public Page<Buyer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Buyer findById(Long id) {
        return null;
    }

    @Override
    public List<Buyer> findAll() {
        return null;
    }
}
