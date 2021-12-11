package com.waa.AmazonMini.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NonNull
    private String street;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    @Size(max=5, min=5)
    private int zipcode;

}
