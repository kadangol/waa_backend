package com.waa.AmazonMini.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    @GeneratedValue
    @Id
    private long Id;

    @NonNull
    private int points;

    @ManyToMany
    @JoinTable(
            name = "BuyersFollowedSellers",
            joinColumns = @JoinColumn(name="buyer_id", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name="seller_id", referencedColumnName = "Id"))
    private List<Seller> followedSellers;

    @OneToMany
    @JoinColumn(name="buyer_review")
    private List<Review> reviews;

    @Embedded
    @NonNull
    private Address shippingAddress;

    @OneToMany(mappedBy = "buyer")
//    @JsonBackReference
    private List<OrderLine> orderLines;


    @JsonIgnore
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;


}
