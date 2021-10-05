package com.meli.quasar.Quasar.Operation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * This class represents a message of each Satellite.
 */
@Data
@NoArgsConstructor
@Entity(name = "messages")
public class Message implements Serializable {

    private static final long serialVersionUID = -5212877359047643353L;
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    String message;

    @NonNull
    int satelliteId;
}
