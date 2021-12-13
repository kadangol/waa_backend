package com.waa.AmazonMini.repository;

import com.waa.AmazonMini.domain.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {
}
