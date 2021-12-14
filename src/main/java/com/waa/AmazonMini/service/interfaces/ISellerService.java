package com.waa.AmazonMini.service.interfaces;

import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.dto.SellerSaveDTO;

public interface ISellerService {
    Seller registerSeller(SellerSaveDTO dto);
}
