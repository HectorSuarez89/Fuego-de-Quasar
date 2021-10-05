package com.meli.quasar.Quasar.Operation.services;

import com.meli.quasar.Quasar.Operation.domain.Distance;
import com.meli.quasar.Quasar.Operation.domain.Position;
import com.meli.quasar.Quasar.Operation.domain.repository.DistanceRepository;
import com.meli.quasar.Quasar.Operation.services.location.LocationService;
import com.meli.quasar.Quasar.Operation.services.location.LocationServiceImpl;
import com.meli.quasar.Quasar.Operation.services.satellite.SatelliteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    private LocationService service;

    @Mock
    private SatelliteService satelliteService;

    @Mock
    private DistanceRepository distanceRepository;

    @BeforeEach
    public void setUp() {
        service = new LocationServiceImpl(
                distanceRepository,
                satelliteService);
    }

    @Test
    public void checkCalculatePositionCorrect(){
        //Given
        double currentdistance = 100.0;
        Distance kenobiDistance = new Distance();
        kenobiDistance.setLength(currentdistance);
        kenobiDistance.setSatelliteId(1);

        Distance skywalkerDistance = new Distance();
        skywalkerDistance.setLength(115.5);
        skywalkerDistance.setSatelliteId(2);

        Distance satoDistance = new Distance();
        satoDistance.setLength(142.7);
        satoDistance.setSatelliteId(3);

        Position kenobiPosition = new Position(1, -500.0,-200.0);
        Position skywalkerPosition = new Position(2, 100.0,-100.0);
        Position satoPosition = new Position(3, 500.0,100.0);

        List<Distance> storedDistances = List.of(kenobiDistance, skywalkerDistance, satoDistance);

        given(distanceRepository.findByLength(currentdistance))
                .willReturn(kenobiDistance);

        given(distanceRepository.findAll())
                .willReturn(storedDistances);

        given(satelliteService.findPositionBySatelllite(kenobiDistance.getSatelliteId()))
                .willReturn(kenobiPosition);
        given(satelliteService.findPositionBySatelllite(skywalkerDistance.getSatelliteId()))
                .willReturn(skywalkerPosition);
        given(satelliteService.findPositionBySatelllite(satoDistance.getSatelliteId()))
                .willReturn(satoPosition);

        double[] expectedResult = {-58.315252587138595, -69.55141837312165};

        //when
        double[] result = service.getLocation(currentdistance);

        //then
        then(result).isEqualTo(expectedResult);
    }
}
