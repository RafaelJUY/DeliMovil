package com.delimovil.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_data")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "FK_User_Role"))
    private Role role;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;
}
