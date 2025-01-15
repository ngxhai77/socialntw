package com.example.socialntw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Areas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer areaId;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Moderator> moderators;

    public Area(Integer areaId) {
        this.areaId = areaId;
    }

    public void setIsActive(boolean b) {
    }
}