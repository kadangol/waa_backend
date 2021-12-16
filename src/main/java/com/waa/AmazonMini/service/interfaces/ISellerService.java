package com.waa.AmazonMini.service.interfaces;

import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.dto.SellerSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface ISellerService {
    Seller registerSeller(SellerSaveDTO dto) throws Exception;

    String ApproveSeller(long Id);

    String RejectSeller(long Id);

    Page<Seller> findAll(@PageableDefault(size = 50) Pageable pageable);

    Seller getSeller(long id);
}
