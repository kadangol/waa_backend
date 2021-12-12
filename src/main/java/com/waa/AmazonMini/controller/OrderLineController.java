package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.service.OrderLineService;
import com.waa.AmazonMini.utils.LocalUtil;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderline")
public class OrderLineController {
    private OrderLineService orderLineService;

    @PostMapping("{id}")
    public ResponseMessage addProduct(@PathVariable Long id, int quantity){
        orderLineService.addProduct(id, quantity);
        return new ResponseMessage( quantity + "Item added");
    }

    @PutMapping("{id}")
    public ResponseMessage editOrder(@PathVariable Long id){
        orderLineService.removeProduct(id);
        return new ResponseMessage("Item removed");
    }

    @DeleteMapping("cancelorder/{id}")
    public ResponseMessage cancelOrder(@PathVariable Long id){
        orderLineService.cancelOrder(id);
        return new ResponseMessage("Item removed");
    }

}



