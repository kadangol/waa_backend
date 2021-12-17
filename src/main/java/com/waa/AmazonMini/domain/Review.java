package com.waa.AmazonMini.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.waa.AmazonMini.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @GeneratedValue
    @Id
    private long id;

    @NonNull
    private String comment;

    @NonNull
    private Status status;


    @OneToOne
    @JoinColumn(name = "productId", nullable = false)
    @JsonBackReference
    private Product product;

    @OneToOne
    @JoinColumn(name = "buyerId", nullable = false)
    @JsonBackReference
    private Buyer buyer;


}
