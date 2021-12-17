package com.waa.AmazonMini.repository;

import com.waa.AmazonMini.domain.Review;
import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT e FROM Review e WHERE e.status = :approvalStatus")
    List<Review> findReviewByStatus(Status approvalStatus);

    @Query(value = "SELECT e FROM Review e WHERE e.product.id = :productId and e.status = :status")
    List<Review> findReviewByProductId(long productId, Status status);
}
