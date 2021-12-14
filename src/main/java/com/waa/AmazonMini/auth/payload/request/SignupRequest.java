package com.waa.AmazonMini.auth.payload.request;

import com.waa.AmazonMini.auth.model.Role;
import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.domain.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String fullName;


    private String phone ;

    private Seller seller;

    private Buyer buyer;

    private Set<Role> roles;


}
