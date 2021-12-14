package com.waa.AmazonMini.domain;

import com.waa.AmazonMini.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller{

    @GeneratedValue
    @Id
    private long Id;

    @NonNull
    private Status ApprovalStatus;

    @OneToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;
}
