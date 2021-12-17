package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.domain.Review;
import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.dto.ReviewSaveDto;
import com.waa.AmazonMini.dto.SellerSaveDTO;
import com.waa.AmazonMini.service.ReviewService;
import com.waa.AmazonMini.service.SellerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review saveReview(@RequestBody ReviewSaveDto dto) {
        return reviewService.save(dto);
    }

    @PutMapping("/approve/{reviewId}")
    public ResponseMessage approveReview(@PathVariable long reviewId) {
        return reviewService.approveReview(reviewId, Status.APPROVED);
    }

    @PutMapping("/reject/{reviewId}")
    public ResponseMessage rejectReview(@PathVariable long reviewId) {
        return reviewService.approveReview(reviewId, Status.REJECTED);
    }

    @GetMapping("/notapprovedyet")
    public List<Review> notApprovedYetReview() {
        return reviewService.findReviewByStatus(Status.NOTAPPROVEDYET);
    }


    @GetMapping("/approved")
    public List<Review> approvedReview() {
        return reviewService.findReviewByStatus(Status.APPROVED);
    }

    @GetMapping("/rejected")
    public List<Review> rejectedReview() {
        return reviewService.findReviewByStatus(Status.REJECTED);
    }

    @GetMapping("/product/{productId}")
    public List<Review> findReviewByProductId(@PathVariable long productId) {
        return reviewService.findReviewByProductId(productId);
    }
}
