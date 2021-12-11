package com.waa.AmazonMini.domain;

import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    @GeneratedValue
    @Id
    private long id;
    @NonNull
    private int quantity;
    @NonNull
    private String billingAddress; //TODO not sure what is BillingAddress
    @NonNull
    private LocalDateTime purchasedTime;
    @NonNull
    private LocalDateTime deliveredTime;
    @NonNull
    private ShippingStatus shippingStatus;
    @NonNull
    private ShippingStatus orderStatus;
}