package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.domain.Address;
import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.domain.Product;
import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineSaveDTO {
    @NonNull
    private int quantity;
    @NonNull
    private Address shippingAddress;//TODO not sure what is BillingAddress
    @NonNull
    private long productId;
}
