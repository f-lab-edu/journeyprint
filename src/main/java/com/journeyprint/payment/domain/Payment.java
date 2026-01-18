package com.journeyprint.payment.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@NoArgsConstructor(access = PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    private Payment(Long profileId, String name, String description) {
        this.profileId = profileId;
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public static Payment of(Long profileId, String name, String description) {
        return new Payment(profileId, name, description);
    }
}