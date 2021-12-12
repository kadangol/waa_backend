package com.waa.AmazonMini.service.interfaces;

import com.waa.AmazonMini.domain.OrderLine;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.dto.ProductSaveDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderLineService {
    public List<OrderLine> findAll();
    ResponseMessage addProduct(Long id, int quantity);

    ResponseMessage removeProduct(Long id);

    ResponseMessage cancelOrder(Long id);




}
