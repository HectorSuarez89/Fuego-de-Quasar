package com.meli.quasar.Quasar.Operation.controller;

import com.meli.quasar.Quasar.Operation.domain.Secret;
import com.meli.quasar.Quasar.Operation.domain.dto.SatelliteDTO;
import com.meli.quasar.Quasar.Operation.domain.wrapper.SatellitesWrapper;
import com.meli.quasar.Quasar.Operation.exceptions.NotEnoughInformation;
import com.meli.quasar.Quasar.Operation.services.secret.SecretService;
import com.meli.quasar.Quasar.Operation.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
@Api(value = Constants.SWAGGER_TITLE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SecretController {

    private final SecretService service;

    @ApiOperation(value = "Receives information from a list of satellites and return Secret decoded")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Secret.class),
            @ApiResponse(code = 400, message = "The content of the messages do not match."),
            @ApiResponse(code = 404, message = "There is not enough information.")
})
    @PostMapping(value = "/topsecret")
    public ResponseEntity<Secret> getSecret(@RequestBody SatellitesWrapper satellites){
        Secret secret = service.findSecret(satellites.getSatellites());
        return null != secret ? ResponseEntity.ok(secret) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Receives information from each satellite and return Secret decoded")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Secret.class),
            @ApiResponse(code = 400, message = "The content of the messages do not match."),
            @ApiResponse(code = 404, message = "There is not enough information.")
    })
    @RequestMapping(value = {"/topsecret_split/","/topsecret_split/{name}"}, method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<Secret> getSecret(@RequestBody SatelliteDTO satellite, @PathVariable(value="name", required = false) String name ){
        if(name == null){
            throw new NotEnoughInformation();
        }
        satellite.setName(name);
        Secret secret = service.findSecret(satellite);
        return null != secret  ? ResponseEntity.ok(secret): ResponseEntity.notFound().build();
    }
}
