package com.waa.AmazonMini.domain;

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
    private Address billingAddress;//TODO not sure what is BillingAddress
    @NonNull
    private LocalDateTime purchasedTime;
    @NonNull
        private ShippingStatus shippingStatus;
        @NonNull
        private ShippingStatus orderStatus;
        @ManyToMany
        private List<Product> products;
    }

