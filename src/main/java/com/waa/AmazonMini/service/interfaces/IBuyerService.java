package com.waa.AmazonMini.service.interfaces;

import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.dto.BuyerSaveDTO;
import com.waa.AmazonMini.dto.BuyerUpdateDTO;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface IBuyerService {
    ResponseMessage save(BuyerSaveDTO productSaveDTO);

    ResponseMessage update(BuyerUpdateDTO productUpdateDTO);

    ResponseMessage delete(Long id);

    Page<Buyer> findAll(Pageable pageable);

    Buyer findById(Long id);

    List<Buyer> findAll();
}