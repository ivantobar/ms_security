package com.uc.ms_security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint( // esto es para que no se repita el mismo rol para un
                                                                   // mismo usuario
                name = "uk_user_role", columnNames = { "user_id", "role_id" }))

@Getter
@Setter
@NoArgsConstructor
public class UserRole {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "role_id", nullable = false)
        private Role role;
}
