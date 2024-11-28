package com.delimovil.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_app_id")
    private User user_id;
    @Column(length = 45)
    private String firstName;
    @Column(length = 45)
    private String lastName;
    @Column(length = 20)
    private String phone;

    @Column(length = 250)
    private String imageUrl;

}
