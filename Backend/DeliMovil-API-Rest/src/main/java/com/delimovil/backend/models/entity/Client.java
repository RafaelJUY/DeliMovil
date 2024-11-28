package com.delimovil.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_app_id")
    private User user_id;

    @Column(length = 45)
    private String first_name;
    @Column(length = 45)
    private String last_name;
    @Column(length = 20)
    private String phone;
    @Column(length = 45)
    private String latitude;
    @Column(length = 45)
    private String longitude;
    @Column(length = 45)
    private String name_street;
    @Column(length = 45)
    private String number_street;
    @Column(length = 5)
    private String floor_department;

    @Column(length = 250)
    private String imageUrl;
}
