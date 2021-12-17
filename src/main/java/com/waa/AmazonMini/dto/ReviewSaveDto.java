package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveDto {

    private String comment;

    private long productId;

    private long buyerId;

}
