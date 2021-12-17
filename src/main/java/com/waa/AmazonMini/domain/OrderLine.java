package com.waa.AmazonMini.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private int quantity;
    @NonNull
    @Embedded
    private Address shippingAddress;//TODO not sure what is BillingAddress
    @NonNull
    private LocalDateTime purchasedTime;
    @NonNull

    private LocalDateTime deliveredTime;
    @NonNull
    private ShippingStatus shippingStatus;
    @NonNull
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name="buyer_id")
//    @JsonManagedReference
    @JsonIgnore
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
}
