package com.waa.AmazonMini.domain;

import com.waa.AmazonMini.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.tomcat.jni.User;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller  extends User {

    @GeneratedValue
    @Id
    private long Id;

    @NonNull
    private Status status;
    @Email
    @NonNull
    private String email;
}
