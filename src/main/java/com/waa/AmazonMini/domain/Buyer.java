package com.waa.AmazonMini.domain;

import com.waa.AmazonMini.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buyer extends Users{

    @GeneratedValue
    @Id
    private long Id;

    @NonNull
    private int points;

//    @ManyToMany
//    private List<Seller> followedSellers;
//
//    @OneToMany
//    private List<Review> reviews;
//
//    @OneToMany
//    private List<OrderLine> orderLines;

}
