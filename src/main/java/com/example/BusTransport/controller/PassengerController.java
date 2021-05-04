package com.example.BusTransport.controller;

import com.example.BusTransport.domain.Passenger;
import com.example.BusTransport.exception.PassengerNotFoundException;
import com.example.BusTransport.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.example.BusTransport.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Pasajeros", description = "Listado de pasajeros")
public class PassengerController {

    private final Logger logger = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    private PassengerService passengerservice;

    @Operation(summary = "Obtiene el listado de pasajeros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de pasajeros",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Passenger.class)))),
    })
    @GetMapping(value = "/passengers", produces = "application/json")
    public ResponseEntity<ArrayList<Passenger>> getPassenger(){
        logger.info("inicio getPassengers");
        logger.info("fin getPassengers");
        return new ResponseEntity<>(passengerservice.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un pasajero por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el pasajero", content = @Content(schema = @Schema(implementation = Passenger.class))),
            @ApiResponse(responseCode = "404", description = "El pasajero no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/passengers/{id}", produces = "application/json")
    public ResponseEntity<Passenger> getPassenger(@PathVariable Integer id) {
        Passenger passenger = passengerservice.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));

        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @Operation(summary = "Almacena un pasajero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "pasajero almacenado", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/passengers", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger){
        if (passengerservice.findById(passenger.getId()).isEmpty()){
            new PassengerNotFoundException(passenger.getId());
        }
        logger.info(passenger.toString());
        return new ResponseEntity<>(passengerservice.save(passenger), HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina un pasajero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimino el pasajero", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El pasajeo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/passengers/{id}", produces = "application/json")
    public ResponseEntity<Response> deletePassenger(@PathVariable Integer id) {
        passengerservice.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "actualiza el nombre de un pasajero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el nombre del pasajero", content = @Content(schema = @Schema(implementation = Passenger.class))),
            @ApiResponse(responseCode = "404", description = "el pasajero no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/passengers/{name}/change-code", produces = "application/json")
    public ResponseEntity<Passenger> modifyPassengerName(@PathVariable Integer id, @RequestBody String name) {
        Passenger passenger = passengerservice.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
        passenger.setName(name);
        return new ResponseEntity<>(passengerservice.save(passenger), HttpStatus.CREATED);
    }

    @Operation(summary = "actualiza unpasajero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el pasajero", content = @Content(schema = @Schema(implementation = Passenger.class))),
            @ApiResponse(responseCode = "404", description = "el pasajero no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/modifypassenger", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Passenger> modifyPassenger(@RequestBody Passenger passenger){
        Integer id = passenger.getId();
        passenger = passengerservice.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
        logger.info(passenger.toString());
        return new ResponseEntity<>(passengerservice.save(passenger), HttpStatus.CREATED);
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(PassengerNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
