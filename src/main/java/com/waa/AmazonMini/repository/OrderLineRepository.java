package com.waa.AmazonMini.repository;

import com.waa.AmazonMini.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
