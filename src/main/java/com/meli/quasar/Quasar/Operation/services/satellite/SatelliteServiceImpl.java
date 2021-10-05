package com.meli.quasar.Quasar.Operation.services.satellite;

import com.meli.quasar.Quasar.Operation.domain.Position;
import com.meli.quasar.Quasar.Operation.domain.Satellite;
import com.meli.quasar.Quasar.Operation.domain.repository.SatelliteRepository;
import com.meli.quasar.Quasar.Operation.exceptions.SatelliteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SatelliteServiceImpl implements SatelliteService{

    private final SatelliteRepository satelliteRepository;

    @Override
    public Satellite findByName(String name) {
        return satelliteRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new SatelliteNotFoundException(name));
    }

    @Override
    public Position findPositionBySatelllite(int satelliteId) {
        return satelliteRepository.findPositionById(satelliteId).getPosition();
    }
}
