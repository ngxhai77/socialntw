package com.example.socialntw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Moderators", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "area_id"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moderator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moderatorId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime addedAt;
}