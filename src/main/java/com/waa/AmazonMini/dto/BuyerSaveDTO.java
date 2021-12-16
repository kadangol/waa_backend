package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerSaveDTO {

    @NotNull
    private String UserName;

    @NotNull
    private String Password;

    @NotNull
    private String FullName;

    @NotNull
    @Email
    private String EmailAddress ;

    private String PhoneNo ;

    @NonNull
    private Address shippingAddress;

}
