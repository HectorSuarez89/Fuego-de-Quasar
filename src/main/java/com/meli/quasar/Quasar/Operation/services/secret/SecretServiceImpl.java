package com.meli.quasar.Quasar.Operation.services.secret;

import com.meli.quasar.Quasar.Operation.domain.*;
import com.meli.quasar.Quasar.Operation.domain.dto.SatelliteDTO;
import com.meli.quasar.Quasar.Operation.exceptions.NotEnoughInformation;
import com.meli.quasar.Quasar.Operation.services.location.LocationService;
import com.meli.quasar.Quasar.Operation.services.message.MessageService;
import com.meli.quasar.Quasar.Operation.services.satellite.SatelliteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecretServiceImpl implements SecretService{

    private final SatelliteService satelliteService;
    private final MessageService messageService;
    private final LocationService locationService;

    @Override
    public Secret findSecret(List<SatelliteDTO> satellites){
        String messageSecret = decodeMessage(satellites);
        Position position = findPosition(satellites);
        return getSecret(messageSecret, position);
    }

    @Override
    public Secret findSecret(SatelliteDTO satellite){
        if(satellite.getName() == null){
            throw new NotEnoughInformation();
        }
        String messageSecret = decodeMessage(satellite);
        Position position = findPosition(satellite);
        return getSecret(messageSecret, position);
    }

    private Secret getSecret(String messageSecret, Position position) {
        Secret secret = null;
        if((messageSecret != null) && (null != position.getX())) {
            secret = new Secret();
            secret.setMessage(messageSecret);
            secret.setPosition(position);

            log.info("Secret has been assembled and reported");

            destroyConfidencialInformation();
        }else{
            throw new NotEnoughInformation();
        }
        return secret;
    }

    private void destroyConfidencialInformation() {
        log.info("Confidencial information will be destroy");
        messageService.destroyMessages();
        locationService.destroyDistances();
    }

    private Position findPosition(List<SatelliteDTO> satellites) {
        Position secretPosition = null;
        for(SatelliteDTO dto: satellites) {
            secretPosition = findPosition(dto);
        }
        return secretPosition;
    }

    private Position findPosition(SatelliteDTO dto) {
        Position secretPosition = new Position();
        Satellite satellite = satelliteService.findByName(dto.getName());
        saveLocationData(dto.getDistance(),satellite.getId());
        double[] calculatedPosition = locationService.getLocation(dto.getDistance());
        if(calculatedPosition != null) {
            secretPosition.setX(calculatedPosition[0]);
            secretPosition.setY(calculatedPosition[1]);
        }
        return secretPosition;
    }

    private String decodeMessage(List<SatelliteDTO> satellites){
        String secretMessage;
        int i = 0;
        do {
            secretMessage = decodeMessage(satellites.get(i));
            i++;
        } while ((secretMessage == null) && (i < satellites.size()));
        return secretMessage;
    }

    private String decodeMessage(SatelliteDTO dto){
        String secretMessage;
        Satellite satellite = satelliteService.findByName(dto.getName());
        saveMesssageData(dto.getMessage(),satellite.getId());
        secretMessage = messageService.getMessage(dto.getMessage());
        return secretMessage;
    }

    private void saveLocationData(double distance, int satellite_id){
        locationService.saveDistance(distance, satellite_id);
    }

    private void saveMesssageData(String[] message, int satellite_id){
        messageService.save(message, satellite_id);
    }
}
