package com.meli.quasar.Quasar.Operation.domain.repository;

import com.meli.quasar.Quasar.Operation.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Handles data operations with Positions
 */
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
