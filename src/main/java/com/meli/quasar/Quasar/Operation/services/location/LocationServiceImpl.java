package com.meli.quasar.Quasar.Operation.services.location;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.quasar.Quasar.Operation.domain.Distance;
import com.meli.quasar.Quasar.Operation.domain.Position;
import com.meli.quasar.Quasar.Operation.domain.repository.DistanceRepository;
import com.meli.quasar.Quasar.Operation.services.satellite.SatelliteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    public static final int MINIMUM_NUMBER_OF_DISTANCES_TO_CALCULATE = 3;
    private final DistanceRepository distanceRepository;
    private final SatelliteService satelliteService;

    @Override
    public double[] getLocation(double distance) {

        Distance currentDistance = distanceRepository.findByLength(distance);
        Position currentPosition = satelliteService.findPositionBySatelllite(currentDistance.getSatelliteId());
        log.info(String.format("Adding %s of distance to (%s,%s) to calculate secret position",currentDistance.getLength(), currentPosition.getX(), currentPosition.getY()) );
        List<Distance> distancesList = distanceRepository.findAll();
        List<Position> positionsList = distancesList.stream()
                .map(d -> satelliteService.findPositionBySatelllite(d.getSatelliteId()))
                .collect(Collectors.toList());

        return distancesList.size() >= MINIMUM_NUMBER_OF_DISTANCES_TO_CALCULATE ?
                calculatePosition(distancesList, positionsList) : null;
    }

    private double[] calculatePosition(List<Distance> distancesList, List<Position> positionsList) {
        double[][] positions = positionsList.stream()
                .map(p -> new double[]{p.getX(), p.getY()}).toArray(size -> new double[size][1]);

        double[] distances = distancesList.stream().mapToDouble(Distance::getLength).toArray();

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        return optimum.getPoint().toArray();
    }

    @Override
    public Distance saveDistance(double distanceLength, int satellite_id) {
        Distance distance = distanceRepository.findBySatelliteId(satellite_id);
        if (distance == null) {
            distance = new Distance();
            distance.setSatelliteId(satellite_id);
        }
        distance.setLength(distanceLength);
        return distanceRepository.save(distance);
    }

    @Override
    public void destroyDistances() {
        distanceRepository.deleteAll();
    }
}
