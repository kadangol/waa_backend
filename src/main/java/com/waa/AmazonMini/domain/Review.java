package com.waa.AmazonMini.domain;


import com.waa.AmazonMini.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @GeneratedValue
    @Id
    private long id;
    @NonNull
    @Size(max = 1, min = 1)
    private int scale;

    @NonNull
    private String comment;

    @NonNull
    private Status status;
}
