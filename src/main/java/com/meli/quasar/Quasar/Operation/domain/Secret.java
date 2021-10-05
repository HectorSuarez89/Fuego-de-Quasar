package com.meli.quasar.Quasar.Operation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the confidential secret.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Secret {
    Position position;
    String message;
}
