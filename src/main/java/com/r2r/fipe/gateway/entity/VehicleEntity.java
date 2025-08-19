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
@Table(name = "vehicle",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_vehicle_brand_modelcode", columnNames = {"brand_id", "fipe_model_code"})
        },
        indexes = {
                @Index(name = "idx_vehicle_brand", columnList = "brand_id"),
                @Index(name = "idx_vehicle_model_name", columnList = "model_name")
        })
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Relacionamento com a marca */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false, foreignKey = @ForeignKey(name = "fk_vehicle_brand"))
    private BrandEntity brand;

    /** Código do modelo na FIPE (ex.: 5585) */
    @Column(name = "fipe_model_code", nullable = false)
    private Integer fipeModelCode;

    /** Nome do modelo (ex.: "AMAROK CD2.0 ...") */
    @Column(name = "model_name", length = 180, nullable = false)
    private String modelName;

    /** Tipo redundante para consultas rápidas (além de brand.type) */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 12, nullable = false)
    private VehicleType type;

    /** Campo editável via API-1 (1.8) */
    @Column(name = "observacoes", columnDefinition = "text")
    private String observacoes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
