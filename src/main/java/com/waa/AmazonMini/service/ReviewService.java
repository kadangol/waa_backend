package com.waa.AmazonMini.service;

import com.waa.AmazonMini.auth.repository.UserRepository;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.domain.Review;
import com.waa.AmazonMini.dto.ReviewSaveDto;
import com.waa.AmazonMini.repository.BuyerRepository;
import com.waa.AmazonMini.repository.ProductRepository;
import com.waa.AmazonMini.repository.ReviewRepository;
import com.waa.AmazonMini.service.interfaces.IReviewService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.Status;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyerService buyerService;
    @Autowired
    UserRepository userRepository;


    public Review save(ReviewSaveDto dto) {
        var review = new Review();
        review.setComment(dto.getComment());
        review.setStatus(Status.NOTAPPROVEDYET);

        var p = productRepository.getById(dto.getProductId());
        review.setProduct(p);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        var user = userRepository.findByUsername(userDetails.getUsername());
        var buyer = buyerService.getBuyerByUserId(user.get().getId());
        review.setBuyer(buyer);

        reviewRepository.save(review);

        return review;
    }


    public ResponseMessage approveReview(long reviewId, Status status) {
        var review = reviewRepository.getById(reviewId);
        review.setStatus(status);
        reviewRepository.save(review);
        return new ResponseMessage("Status updated to: " + status, HttpStatus.OK);
    }

    public List<Review> findReviewByStatus(Status status) {
        return reviewRepository.findReviewByStatus(status);
    }


    public List<Review> findReviewByProductId(Long productId) {
        return reviewRepository.findReviewByProductId(productId, Status.APPROVED);
    }

}
