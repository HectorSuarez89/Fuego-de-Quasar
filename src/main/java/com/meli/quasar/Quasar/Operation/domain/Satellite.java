package com.meli.quasar.Quasar.Operation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * This class represents a Satellite.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "satellites")
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @OneToOne
    @JoinColumn(name="position_id", insertable = false, updatable = false)
    private Position position;
}
