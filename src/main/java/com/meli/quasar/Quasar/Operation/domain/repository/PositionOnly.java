package com.meli.quasar.Quasar.Operation.domain.repository;

import com.meli.quasar.Quasar.Operation.domain.Position;

/**
 * @return only @{@link Position} object of satellite.
 */
public interface PositionOnly {
    Position getPosition();
}
