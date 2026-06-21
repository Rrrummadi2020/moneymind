package com.kgpalle.moneymind.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;

@Entity
@Table(name = "ROLES")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_seq_gen"
    )
    @SequenceGenerator(
            name = "role_seq_gen",
            sequenceName = "role_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.isActive = "Y";
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
    @Override
    public @Nullable String getAuthority() {
        return name;
    }
}
