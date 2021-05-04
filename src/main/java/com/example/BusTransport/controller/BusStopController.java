package com.example.BusTransport.controller;

import com.example.BusTransport.domain.BusStop;
import com.example.BusTransport.exception.BusStopNotFoundException;
import com.example.BusTransport.service.BusStopService;
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
@Tag(name = "Paradas de autobus", description = "Listado de paradas de autobus")
public class BusStopController {

    private final Logger logger = LoggerFactory.getLogger(BusStopController.class);

    @Autowired
    private BusStopService busstopservice;

    @Operation(summary = "Obtiene el listado de paradas de autobuses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de paradas de autobuses",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BusStop.class)))),
    })
    @GetMapping(value = "/busstops", produces = "application/json")
    public ResponseEntity<ArrayList<BusStop>> getBusStop(){
        logger.info("inicio getBuses");
        logger.info("fin getBuses");
        return new ResponseEntity<>(busstopservice.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una parada de autobus por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la parada de autobus", content = @Content(schema = @Schema(implementation = BusStop.class))),
            @ApiResponse(responseCode = "404", description = "La parada de autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/busstops/{id}", produces = "application/json")
    public ResponseEntity<BusStop> getBusStopById(@PathVariable Integer id) {
        BusStop busstop = busstopservice.findById(id)
                .orElseThrow(() -> new BusStopNotFoundException(id));
        return new ResponseEntity<>(busstop, HttpStatus.OK);
    }

    @Operation(summary = "Almacena una parada de autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "parada de autobus almacenado", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/busstops", produces = "application/json", consumes = "application/json")
    public ResponseEntity<BusStop> addBusStop(@RequestBody BusStop busstop){
        if (busstopservice.findById(busstop.getId()).isEmpty()){
            new BusStopNotFoundException(busstop.getId());
        }
        logger.info(busstop.toString());
        return new ResponseEntity<>(busstopservice.save(busstop), HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina una parada de autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la parada de autobus", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La parada de autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/busstops/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteBusStop(@PathVariable Integer id) {
        busstopservice.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "actualiza el numero de asientos de una parada de autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el numero de asientos de una parada de autobus", content = @Content(schema = @Schema(implementation = BusStop.class))),
            @ApiResponse(responseCode = "404", description = "La parada de autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/busstops/{seat}/change-code", produces = "application/json")
    public ResponseEntity<BusStop> modifyBusStopSeat(@PathVariable Integer id, @RequestBody Integer seat) {
        BusStop busstop = busstopservice.findById(id)
                .orElseThrow(() -> new BusStopNotFoundException(id));
        busstop.setSeat(seat);
        return new ResponseEntity<>(busstopservice.save(busstop), HttpStatus.CREATED);
    }

    @Operation(summary = "actualiza una parada de autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo la parada de autobus", content = @Content(schema = @Schema(implementation = BusStop.class))),
            @ApiResponse(responseCode = "404", description = "La parada de autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/modifybusstop", produces = "application/json", consumes = "application/json")
    public ResponseEntity<BusStop> modifyBusStop(@RequestBody BusStop busstop){
        Integer id = busstop.getId();
        busstop = busstopservice.findById(id)
                .orElseThrow(() -> new BusStopNotFoundException(id));
        logger.info(busstop.toString());
        return new ResponseEntity<>(busstopservice.save(busstop), HttpStatus.CREATED);
    }

    @ExceptionHandler(BusStopNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(BusStopNotFoundException bsnfe) {
        Response response = Response.errorResonse(NOT_FOUND, bsnfe.getMessage());
        logger.error(bsnfe.getMessage(), bsnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
