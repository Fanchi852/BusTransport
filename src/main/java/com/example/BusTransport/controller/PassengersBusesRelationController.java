package com.example.BusTransport.controller;

import com.example.BusTransport.domain.PassengersBusesRelation;
import com.example.BusTransport.exception.PassengersBusesRelationNotFoundException;
import com.example.BusTransport.service.PassengersBusesRelationService;
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
@Tag(name = "Relaccion entre pasajeros y autobuses", description = "Listado de relacciones entre pasajeros y autobuese")
public class PassengersBusesRelationController {

    private final Logger logger = LoggerFactory.getLogger(PassengersBusesRelationController.class);

    @Autowired
    private PassengersBusesRelationService passengersbusesrelationservice;

    @Operation(summary = "Obtiene el listado de relaciones entre autobuses y pasajeros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de autobuses",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PassengersBusesRelation.class)))),
    })
    @GetMapping(value = "/passengersbusesrelations", produces = "application/json")
    public ResponseEntity<ArrayList<PassengersBusesRelation>> getPassengersBusesRelation(){
        logger.info("inicio getBuses");
        logger.info("fin getBuses");
        return new ResponseEntity<>(passengersbusesrelationservice.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una relacione entre autobuses y pasajeros por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la relacione entre autobuses y pasajeros", content = @Content(schema = @Schema(implementation = PassengersBusesRelation.class))),
            @ApiResponse(responseCode = "404", description = "La relacione entre autobuses y pasajeros no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/passengersbusesrelations/{id}", produces = "application/json")
    public ResponseEntity<PassengersBusesRelation> getPassengersBusesRelation(@PathVariable Integer id) {
        PassengersBusesRelation passengersbusesrelation = passengersbusesrelationservice.findById(id)
                .orElseThrow(() -> new PassengersBusesRelationNotFoundException(id));

        return new ResponseEntity<>(passengersbusesrelation, HttpStatus.OK);
    }

    @Operation(summary = "Almacena una relacione entre autobuses y pasajeros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "relacione entre autobuses y pasajeros almacenada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/passengersbusesrelations", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PassengersBusesRelation> addPassengersBusesRelation(@RequestBody PassengersBusesRelation passengersbusesrelation){
        if (passengersbusesrelationservice.findById(passengersbusesrelation.getId()).isEmpty()){
            new PassengersBusesRelationNotFoundException(passengersbusesrelation.getId());
        }
        logger.info(passengersbusesrelation.toString());
        return new ResponseEntity<>(passengersbusesrelationservice.save(passengersbusesrelation), HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina una relacione entre autobuses y pasajeros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la relacione entre autobuses y pasajeros", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La relacione entre autobuses y pasajeros no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/passengersbusesrelations/{id}", produces = "application/json")
    public ResponseEntity<Response> deletePassengersBusesRelation(@PathVariable Integer id) {
        passengersbusesrelationservice.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "actualiza una relacione entre autobuses y pasajeros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo la relacione entre autobuses y pasajeros", content = @Content(schema = @Schema(implementation = PassengersBusesRelation.class))),
            @ApiResponse(responseCode = "404", description = "La relacione entre autobuses y pasajeros no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/modifypassengersbusesrelation", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PassengersBusesRelation> modifyPassengersBusesRelation(@RequestBody PassengersBusesRelation passengersbusesrelation){
        Integer id = passengersbusesrelation.getId();
        passengersbusesrelation = passengersbusesrelationservice.findById(id)
                .orElseThrow(() -> new PassengersBusesRelationNotFoundException(id));
        logger.info(passengersbusesrelation.toString());
        return new ResponseEntity<>(passengersbusesrelationservice.save(passengersbusesrelation), HttpStatus.CREATED);
    }

    @ExceptionHandler(PassengersBusesRelationNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(PassengersBusesRelationNotFoundException pbrnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pbrnfe.getMessage());
        logger.error(pbrnfe.getMessage(), pbrnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
