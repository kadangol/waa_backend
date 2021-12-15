package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.domain.Address;
import com.waa.AmazonMini.domain.Review;
import com.waa.AmazonMini.domain.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerUpdateDTO {

    private long Id;

    private int points;

    private Address shippingAddress;

}
