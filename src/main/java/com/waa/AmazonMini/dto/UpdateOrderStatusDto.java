package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.utils.enums.OrderStatus;
import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusDto {
    long orderLineId;
    OrderStatus orderStatus;
}

