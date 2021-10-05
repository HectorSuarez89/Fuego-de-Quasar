package com.meli.quasar.Quasar.Operation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * This class represents a position on the cartesian plane.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "positions")
public class Position {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    Double x;

    @NonNull
    Double y;
}
