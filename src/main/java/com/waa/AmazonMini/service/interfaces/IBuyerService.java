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


public interface IBuyerService {
//    ResponseMessage save(BuyerSaveDTO buyerSaveDTO);

    ResponseMessage update(BuyerUpdateDTO buyerUpdateDTO);

    ResponseMessage delete(Long id);

    Page<Buyer> findAll(Pageable pageable);

    Buyer findById(Long id);

}
