package com.waa.AmazonMini.repository;

import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "SELECT e FROM Seller e WHERE e.user.Id = :userId")
    Seller findByUserId1(long userId);

    @Query(value = "SELECT e FROM Seller e WHERE e.ApprovalStatus = :approvalStatus")
    List<Seller> findSellerByApprovalStatus(Status approvalStatus);


    @Query(value = "SELECT e FROM Product e WHERE e.seller.Id = :sellerId")
    List<Product> findProductBySeller(long sellerId);
}
