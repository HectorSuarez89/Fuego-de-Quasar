package com.meli.quasar.Quasar.Operation.controller;

import com.meli.quasar.Quasar.Operation.domain.Position;
import com.meli.quasar.Quasar.Operation.domain.Secret;
import com.meli.quasar.Quasar.Operation.domain.dto.SatelliteDTO;
import com.meli.quasar.Quasar.Operation.domain.wrapper.SatellitesWrapper;
import com.meli.quasar.Quasar.Operation.services.secret.SecretService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(SecretController.class)
public class SecretControllerTest {

    @MockBean
    private SecretService service;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<SatellitesWrapper> satellitesWrapperJacksonTester;

    @Autowired
    private JacksonTester<Secret> secretJacksonTester;

    @Test
    public void getSecretTest() throws Exception {
        String[] kenobiMsg = {"este", "", "", "mensaje", ""};
        String[] skywalkerMsg = {"", "es", "", "", "secreto"};
        String[] satoMsg = {"este", "", "un", "", ""};

        SatelliteDTO kenobi = new SatelliteDTO("kenobi", 100.0, kenobiMsg);
        SatelliteDTO skywalker = new SatelliteDTO("skywalker", 115.5, skywalkerMsg);
        SatelliteDTO sato = new SatelliteDTO("sato", 142.7, satoMsg);

        List<SatelliteDTO> satellites = new ArrayList<>();
        Collections.addAll(satellites, kenobi, skywalker, sato);

        SatellitesWrapper wrapper = new SatellitesWrapper(satellites);

        String expectedMessage = "este es un mensaje secreto";
        Position secretPosition = new Position();
        secretPosition.setX(-58.31);
        secretPosition.setY(-69.55);

        Secret expectedSecret = new Secret();
        expectedSecret.setPosition(secretPosition);
        expectedSecret.setMessage(expectedMessage);

        given(service.findSecret(satellites)).willReturn(expectedSecret);

        // when
        MockHttpServletResponse response = mvc.perform(
                        post("/topsecret")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(satellitesWrapperJacksonTester.write(wrapper).getJson()))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                secretJacksonTester.write(
                        expectedSecret
                ).getJson());
    }
}
