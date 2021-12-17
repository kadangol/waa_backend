package com.waa.AmazonMini.repository;

import com.waa.AmazonMini.domain.OrderLine;
import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    @Query(value = "SELECT e FROM OrderLine e WHERE e.buyer.Id = :buyerId and e.orderStatus = :orderStatus")
    List<OrderLine> findOrderByBuyerIdAndStatus(long buyerId, OrderStatus orderStatus);

}
