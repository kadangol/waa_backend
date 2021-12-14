package com.waa.AmazonMini.domain;

import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Users {
    @GeneratedValue
    @Id
    private long Id;
    @NotNull
    @NotEmpty
    private String UserName;
    @NotNull
    @NotEmpty
    private String Password;
    @NotNull
    @NotEmpty
    private String FullName;

    @NotNull
    @NotEmpty
    @Email
    private String EmailAddress ;

    private String PhoneNo ;

    @NotNull
    private boolean IsDeleted;

    @OneToOne(mappedBy = "user")
    private Seller seller;

    @OneToOne(mappedBy = "user")
    private Buyer buyer;


}

