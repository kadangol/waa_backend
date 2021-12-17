package com.waa.AmazonMini.service;

import com.waa.AmazonMini.auth.repository.UserRepository;
import com.waa.AmazonMini.domain.OrderLine;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.dto.OrderLineSaveDTO;
import com.waa.AmazonMini.dto.ProductSaveDTO;
import com.waa.AmazonMini.dto.ProductUpdateDTO;
import com.waa.AmazonMini.repository.OrderLineRepository;
import com.waa.AmazonMini.service.interfaces.IOrderLineService;
import com.waa.AmazonMini.utils.LocalUtil;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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

    private Product product;

    @Override
    public ResponseMessage addProductToCart(OrderLineSaveDTO dto) {
        var product = productService.findById(dto.getProductId());
        if (product == null){
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

}
