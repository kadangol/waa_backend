package com.waa.AmazonMini.service;

import com.waa.AmazonMini.auth.repository.UserRepository;
import com.waa.AmazonMini.dto.UpdateOrderStatusDto;
import com.waa.AmazonMini.dto.UpdateShippingStatusDto;
import com.waa.AmazonMini.domain.OrderLine;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.dto.OrderLineSaveDTO;
import com.waa.AmazonMini.repository.BuyerRepository;
import com.waa.AmazonMini.repository.OrderLineRepository;
import com.waa.AmazonMini.service.interfaces.IOrderLineService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderLineService implements IOrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private EmailService emailService;

//    private Product product;

    @Override
    public ResponseMessage addProductToCart(OrderLineSaveDTO dto) {
        var product = productService.findById(dto.getProductId());
        if (product == null) {
            return new ResponseMessage("Invalid product id.");
        }
        OrderLine orderLine = new OrderLine();
        if (dto.getQuantity() <= product.getQuantity()) {
            orderLine.setQuantity(dto.getQuantity());
        } else
            return new ResponseMessage("Selected quantity is not available.");
        orderLine.setShippingAddress(dto.getShippingAddress());
        orderLine.setOrderStatus(OrderStatus.INTHECART);
        orderLine.setProduct(product);
        orderLine.setShippingStatus(ShippingStatus.PREPARINGTOSHIP);
        orderLine.setPurchasedTime(LocalDateTime.now());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        var user = userRepository.findByUsername(userDetails.getUsername());
        var buyer = buyerService.getBuyerByUserId(user.get().getId());
        orderLine.setBuyer(buyer);
        orderLineRepository.save(orderLine);

        emailService.sendOrderLineStatusChangeEmail(orderLine);
        return new ResponseMessage("Product saved.", HttpStatus.OK, orderLine);
    }

    public List<OrderLine> findOrderByBuyerIdAndStatus(OrderStatus orderStatus) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        var user = userRepository.findByUsername(userDetails.getUsername());
        var buyer = buyerService.getBuyerByUserId(user.get().getId());

        var temp = orderLineRepository.findOrderByBuyerIdAndStatus(buyer.getId(), orderStatus);
        return temp;
    }

    public ResponseMessage purchaseOrder(Long orderLineId) {
        var orderline = orderLineRepository.getById(orderLineId);
        orderline.setPurchasedTime(LocalDateTime.now());
        orderline.setDeliveredTime(LocalDateTime.now().plusDays(7));
        orderline.setShippingStatus(ShippingStatus.PREPARINGTOSHIP);
        orderline.setOrderStatus(OrderStatus.PURCHASED);
        orderLineRepository.save(orderline);

        var buyer = orderline.getBuyer();
        buyer.setPoints(buyer.getPoints() + 10);
        buyerRepository.save(buyer);

        productService.updateQuantity(orderline.getProduct().getId(), (orderline.getProduct().getQuantity() - orderline.getQuantity()));

        emailService.sendOrderLineStatusChangeEmail(orderline);
        return new ResponseMessage("Order Successful.", HttpStatus.OK);
    }

    public ResponseMessage cancelOrder(Long orderLineId) {
        var orderline = orderLineRepository.getById(orderLineId);
        if (orderline.getShippingStatus().equals(ShippingStatus.SHIPPED.toString())) {
            return new ResponseMessage("Order has been shipped. Cannot cancel.", HttpStatus.OK);
        }
        orderline.setOrderStatus(OrderStatus.CANCELLED);
        orderLineRepository.save(orderline);

        emailService.sendOrderLineStatusChangeEmail(orderline);
        return new ResponseMessage("Order Cancelled.", HttpStatus.OK);
    }

    public ResponseMessage updateOrderShippingStatus(UpdateShippingStatusDto dto) {
        var orderline = orderLineRepository.getById(dto.getOrderLineId());
        orderline.setShippingStatus(dto.getShippingStatus());
        orderLineRepository.save(orderline);

        emailService.sendOrderLineStatusChangeEmail(orderline);

        return new ResponseMessage("ShippingStatus updated.", HttpStatus.OK);
    }

    public ResponseMessage updateOrderStatus(UpdateOrderStatusDto dto) {
        var orderline = orderLineRepository.getById(dto.getOrderLineId());
        orderline.setOrderStatus(dto.getOrderStatus());
        orderLineRepository.save(orderline);


        emailService.sendOrderLineStatusChangeEmail(orderline);

        return new ResponseMessage("Order status updated.", HttpStatus.OK);
    }

    public ResponseMessage deleteProductFromCart(Long orderLineId) {
        orderLineRepository.deleteById(orderLineId);
        return new ResponseMessage("Product deleted.", HttpStatus.OK);
    }


}
