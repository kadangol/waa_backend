package com.waa.AmazonMini.dto;

import com.waa.AmazonMini.domain.Photo;
import com.waa.AmazonMini.domain.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveDTO {
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private double pricePerUnit;

    @NonNull
    private Seller seller;

    @NonNull
    private List<Photo> photoes;
}
