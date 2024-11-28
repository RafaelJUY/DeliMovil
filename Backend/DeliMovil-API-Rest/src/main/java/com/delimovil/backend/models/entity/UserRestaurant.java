package com.delimovil.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user_restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class UserRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_app_id")
    private User user_app_id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @Column(length = 45)
    private String firstName;
    @Column(length = 45)
    private String lastName;
}
