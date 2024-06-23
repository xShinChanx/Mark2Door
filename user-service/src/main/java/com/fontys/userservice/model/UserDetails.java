package com.fontys.userservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "userDetails")
@Getter
@Setter
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "houseNo")
    private String houseNo;

    @Column(name = "userID")
    private Long userID;
}