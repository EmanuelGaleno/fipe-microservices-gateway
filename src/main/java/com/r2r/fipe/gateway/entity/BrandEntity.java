package com.r2r.fipe.gateway.entity;

import com.r2r.fipe.gateway.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "brand",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_brand_type_code", columnNames = {"type", "fipe_code"})
        },
        indexes = {
                @Index(name = "idx_brand_type_code", columnList = "type,fipe_code"),
                @Index(name = "idx_brand_name", columnList = "name")
        })
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Código da marca na FIPE (ex.: "59") */
    @Column(name = "fipe_code", length = 16, nullable = false)
    private String fipeCode;

    /** Nome da marca (ex.: "VW - VolksWagen") */
    @Column(name = "name", length = 120, nullable = false)
    private String name;

    /** Tipo de veículo (carros/motos/caminhoes) */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 12, nullable = false)
    private VehicleType type;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}