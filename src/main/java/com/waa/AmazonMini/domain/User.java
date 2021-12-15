package com.waa.AmazonMini.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.waa.AmazonMini.auth.model.Role;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Data
public class User {
    @Id
    @GeneratedValue
    private long Id;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String username;
    @NotNull
    @NotEmpty
    @JsonIgnore
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email ;

    @NotNull
    @NotEmpty
    private String fullName;


    private String phone ;

    @NotNull
    @Column(columnDefinition="int default 0")
    private int isDeleted;

    @JsonBackReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Seller seller;

    @JsonBackReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Buyer buyer;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String email, String fullName, String phone, Seller seller, Buyer buyer, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.seller = seller;
        if(seller != null){
            this.seller.setUser(this);
        }
        this.buyer = buyer;
        if (this.buyer != null){
            this.buyer.setUser(this);
        }
        this.roles = roles;
    }
}

