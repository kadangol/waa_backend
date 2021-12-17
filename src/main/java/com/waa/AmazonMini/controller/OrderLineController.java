package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.OrderLine;
import com.waa.AmazonMini.dto.OrderLineSaveDTO;
import com.waa.AmazonMini.dto.UpdateOrderStatusDto;
import com.waa.AmazonMini.dto.UpdateShippingStatusDto;
import com.waa.AmazonMini.service.OrderLineService;
import com.waa.AmazonMini.utils.LocalUtil;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orderline")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    @PostMapping("/addToCart")
    public ResponseMessage addProductToCart(@RequestBody OrderLineSaveDTO dto) {
        return orderLineService.addProductToCart(dto);
    }

    @GetMapping("/getCart")
    public @ResponseBody
    List<OrderLine> getCart() {
        List<OrderLine> temp = orderLineService.findOrderByBuyerIdAndStatus(OrderStatus.INTHECART);
        return temp;
    }

    @GetMapping("/getPurchasedProducts")
    public List<OrderLine> getPurchasedProducts() {
        return orderLineService.findOrderByBuyerIdAndStatus(OrderStatus.PURCHASED);
    }

    @GetMapping("/getReturnedProducts")
    public List<OrderLine> getReturnedProducts() {
        return orderLineService.findOrderByBuyerIdAndStatus(OrderStatus.RETURNED);
    }

    @GetMapping("/getCancelledProducts")
    public List<OrderLine> getCancelledProducts() {
        return orderLineService.findOrderByBuyerIdAndStatus(OrderStatus.CANCELLED);
    }

    @GetMapping("/getDeliveredProducts")
    public List<OrderLine> getDeliveredProducts() {
        return orderLineService.findOrderByBuyerIdAndStatus(OrderStatus.DELIVEREDSUCCESSFULLY);
    }


    @PutMapping("/purchaseOrder/{orderLineId}")
    public ResponseMessage purchaseOrder(@PathVariable Long orderLineId) {
        return orderLineService.purchaseOrder(orderLineId);
    }

    @PutMapping("/cancelOrder/{orderLineId}")
    public ResponseMessage cancelOrder(@PathVariable Long orderLineId) {
        return orderLineService.cancelOrder(orderLineId);
    }

    @PutMapping("/updateOrderShippingStatus")
    public ResponseMessage updateOrderShippingStatus(@RequestBody UpdateShippingStatusDto dto) {
        return orderLineService.updateOrderShippingStatus(dto);
    }

    @PutMapping("/updateOrderStatus")
    public ResponseMessage updateOrderStatus(UpdateOrderStatusDto dto) {
        return orderLineService.updateOrderStatus(dto);
    }


    @DeleteMapping("/{orderLineId}")
    public ResponseMessage deleteProductFromCart(@PathVariable Long orderLineId) {
        return orderLineService.deleteProductFromCart(orderLineId);
    }

}



