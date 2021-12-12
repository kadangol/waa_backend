package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embedded;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerSaveDTO {
    @NonNull
    private String nickName;
    @Email
    @NonNull
    private String email;
    @NonNull
    private Address shippingAddress;

}
