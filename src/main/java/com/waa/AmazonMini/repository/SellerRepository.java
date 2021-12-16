package com.waa.AmazonMini.repository;

import com.waa.AmazonMini.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "SELECT e FROM Seller e WHERE e.user.Id = :userId")
    Seller findByUserId1(long userId);
}
