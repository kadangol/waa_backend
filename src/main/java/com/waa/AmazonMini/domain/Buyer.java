package com.waa.AmazonMini.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buyer  extends  Users{

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
    private List<OrderLine> orderLines;


    @OneToOne
    @JoinColumn(name = "UserId", nullable = false)
    private Users user;


}
