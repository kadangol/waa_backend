package com.waa.AmazonMini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private double pricePerUnit;

//    @NonNull
//    @ManyToOne //TODO need to fic mappedby ...
//    private Seller seller;

    @NonNull
    @OneToMany
    private List<Photo> photoes;
}
