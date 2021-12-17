package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.utils.enums.ShippingStatus;
import lombok.Data;

@Data
public class UpdateShippingStatusDto {
    long orderLineId;
    ShippingStatus shippingStatus;
}
