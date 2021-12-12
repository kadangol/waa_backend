package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.OrderLine;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.dto.ProductSaveDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.repository.OrderLineRepository;
import com.waa.AmazonMini.service.interfaces.IOrderLineService;
import com.waa.AmazonMini.utils.LocalUtil;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService implements IOrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    private Product product;
    private OrderLine orderLine;

    @Override
    public ResponseMessage addProduct(Long id, int quantity) {
        if(id == product.getId() && quantity <= product.getQuantity()){
            orderLine.setQuantity(quantity);
        }
        return new ResponseMessage(quantity + "item added");
    }

    @Override
    public ResponseMessage removeProduct(Long id) {
        if(id == product.getId() && orderLine.getQuantity() > 0){
            orderLine.setQuantity(orderLine.getQuantity() - 1);
        }
        return new ResponseMessage(quantity + "item removed");
    }

    @Override
    public ResponseMessage cancelOrder(Long id) {
        if(id == product.getId() && orderLine.getQuantity()>0){
            orderLineRepository.deleteById(id);
            orderLine.setQuantity(0);
        }
        return new ResponseMessage("Item removed", HttpStatus.OK, LocalUtil.getMessage("ProductID", product.getId()));
    }

    @Override
    public ResponseMessage orderStatus() {
        return null;
    }
}
