package com.meli.quasar.Quasar.Operation.services;

import com.meli.quasar.Quasar.Operation.domain.Position;
import com.meli.quasar.Quasar.Operation.domain.Satellite;
import com.meli.quasar.Quasar.Operation.domain.Secret;
import com.meli.quasar.Quasar.Operation.domain.dto.SatelliteDTO;
import com.meli.quasar.Quasar.Operation.services.location.LocationService;
import com.meli.quasar.Quasar.Operation.services.message.MessageService;
import com.meli.quasar.Quasar.Operation.services.satellite.SatelliteService;
import com.meli.quasar.Quasar.Operation.services.secret.SecretService;
import com.meli.quasar.Quasar.Operation.services.secret.SecretServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.BDDAssertions.then;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SecretServiceTest {

    private SecretService service;

    @Mock
    private SatelliteService satelliteService;

    @Mock
    private LocationService locationService;

    @Mock
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        service = new SecretServiceImpl(
                satelliteService,
                messageService,
                locationService);

    }

    /*@Test
    public void checkCalcutaleSecretWhenAllDataIsProvided() throws MessageException {
        //Given
        String[] msjkenobi = {"este", "","","mensaje",""};
        String[] msjskywalker = {"", "es", "", "", "secreto"};
        String[] msjsato = {"este", "", "un", "", "secreto"};

        SatelliteDTO dtoKenobi = new SatelliteDTO("kenobi",100.0, msjkenobi);
        SatelliteDTO dtoSkywalker = new SatelliteDTO("skywalker",115.5, msjskywalker);
        SatelliteDTO dtoSato = new SatelliteDTO("sato",142.7, msjsato);

        Satellite kenobi = new Satellite(1, "Kenobi", new Position(1, -500.0,-200.0));
        Satellite skywalker = new Satellite(2, "skywalker", new Position(2, 100.0,-100.0));
        Satellite sato = new Satellite(3, "sato", new Position(3, 500.0,100.0));

        List<SatelliteDTO> satellites = List.of(dtoSato);

        String expectedMessage = "este es un mensaje secreto";
        double[] expectedPosition = {-58.31,-69.55};

        given(satelliteService.findByName("sato"))
                .willReturn(sato);
        given(messageService.getMessage(dtoSato.getMessage()))
                .willReturn(expectedMessage);
        given(locationService.getLocation(dtoSato.getDistance()))
                .willReturn(expectedPosition);

        Position secretPosition = new Position();
        secretPosition.setX(expectedPosition[0]);
        secretPosition.setY(expectedPosition[1]);
        Secret expectedSecret = new Secret();
        expectedSecret.setPosition(secretPosition);
        expectedSecret.setMessage(expectedMessage);

        Secret secret = service.findSecret(satellites);

        then(expectedSecret).isEqualTo(secret);

    }*/

    @Test
    public void checkCorrectSecret() {
        //Given
        String[] msg = {"este", "", "un", "", "secreto"};
        SatelliteDTO dtoSato = new SatelliteDTO("sato",142.7, msg);
        Satellite sato = new Satellite(3, "sato", new Position(3, 500.0,100.0));
        List<SatelliteDTO> satellites = List.of(dtoSato);

        String expectedMessage = "este es un mensaje secreto";
        double[] expectedPosition = {-58.31, -69.55};

        given(satelliteService.findByName("sato"))
                .willReturn(sato);
        given(messageService.getMessage(dtoSato.getMessage()))
                .willReturn(expectedMessage);
        given(locationService.getLocation(dtoSato.getDistance()))
                .willReturn(expectedPosition);

        Position secretPosition = new Position();
        secretPosition.setX(expectedPosition[0]);
        secretPosition.setY(expectedPosition[1]);

        Secret expectedSecret = new Secret();
        expectedSecret.setPosition(secretPosition);
        expectedSecret.setMessage(expectedMessage);

        //When
        Secret secret = service.findSecret(satellites);
        //Then
        then(expectedSecret).isEqualTo(secret);
    }

}
