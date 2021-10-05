package com.meli.quasar.Quasar.Operation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * This class represents a Distance from imperial spaceShip to Satellite.
 */
@Data
@NoArgsConstructor
@Entity(name = "distances")
public class Distance {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @Column(name = "distance_length")
    double length;

    @NonNull
    int satelliteId;

}
