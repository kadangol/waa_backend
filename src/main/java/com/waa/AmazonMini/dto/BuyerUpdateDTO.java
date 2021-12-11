package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerUpdateDTO {
    @NonNull
    private long id;
    @NonNull
    private String nickName;
    @Email
    @NonNull
    private String email;
    @NonNull
    private Address shippingAddress;

}
