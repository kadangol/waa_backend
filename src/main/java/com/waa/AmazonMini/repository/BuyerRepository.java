package com.waa.AmazonMini.repository;

import com.waa.AmazonMini.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
