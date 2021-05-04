package com.example.BusTransport.controller;

import com.example.BusTransport.domain.Bus;
import com.example.BusTransport.exception.BusNotFoundException;
import com.example.BusTransport.service.BusService;
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
import java.util.HashSet;
import java.util.Set;

import static com.example.BusTransport.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Autobuses", description = "Listado de autobuses")
public class BusController {

    private final Logger logger = LoggerFactory.getLogger(BusController.class);

    @Autowired
    private BusService busservice;

    @Operation(summary = "Obtiene el listado de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de autobuses",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Bus.class)))),
    })
    @GetMapping(value = "/buses", produces = "application/json")
    public ResponseEntity<ArrayList<Bus>> getBuses(){
        logger.info("inicio getBuses");
        logger.info("fin getBuses");
        return new ResponseEntity<>(busservice.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un autobus por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el autobus", content = @Content(schema = @Schema(implementation = Bus.class))),
            @ApiResponse(responseCode = "404", description = "El autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/buses/{id}", produces = "application/json")
    public ResponseEntity<Bus> getBusById(@PathVariable Integer id) {
        Bus bus = busservice.findById(id)
                .orElseThrow(() -> new BusNotFoundException(id));

        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un autobus por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el autobus", content = @Content(schema = @Schema(implementation = Bus.class))),
            @ApiResponse(responseCode = "404", description = "El autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/busescostume/{capacity},{code},{consumption}", produces = "application/json")
    public ResponseEntity<ArrayList<Bus>> getBusByCapacityandConsumptionandcode(@PathVariable Integer capacity, String code, Float consumption) {
        Set<Bus> set_aux = new HashSet<>();
        ArrayList<Bus> res = new ArrayList<>();
        if (capacity != null){
            busservice.findByCapacity(capacity).forEach(set_aux::add);
        }
        if (code != null){
            busservice.findByCode(code).forEach(set_aux::add);
        }
        if (consumption != null){
            busservice.findByConsumption(consumption).forEach(set_aux::add);
        }else{
            new BusNotFoundException();
        }
        set_aux.forEach(res::add);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Almacena un autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autobus almacenado", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/buses", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus){
        if (busservice.findById(bus.getId()).isEmpty()){
            new BusNotFoundException(bus.getId());
        }
        logger.info(bus.toString());
        return new ResponseEntity<>(busservice.save(bus), HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina un autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el autobus", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/buses/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteBus(@PathVariable Integer id) {
        busservice.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "actualiza el codigo un autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el codigo del autobus", content = @Content(schema = @Schema(implementation = Bus.class))),
            @ApiResponse(responseCode = "404", description = "El autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/buses/{code}/change-code", produces = "application/json")
    public ResponseEntity<Bus> modifybusCode(@PathVariable Integer id, @RequestBody String code) {
        Bus bus = busservice.findById(id)
                .orElseThrow(() -> new BusNotFoundException(id));
        bus.setCode(code);
        return new ResponseEntity<>(busservice.save(bus), HttpStatus.CREATED);
    }

    @Operation(summary = "actualiza un autobus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el autobus", content = @Content(schema = @Schema(implementation = Bus.class))),
            @ApiResponse(responseCode = "404", description = "El autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/modifybus", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Bus> modifyBus(@RequestBody Bus bus){
        Integer id = bus.getId();
        bus = busservice.findById(id)
                .orElseThrow(() -> new BusNotFoundException(id));
        logger.info(bus.toString());
        return new ResponseEntity<>(busservice.save(bus), HttpStatus.CREATED);
    }

    @ExceptionHandler(BusNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(BusNotFoundException bnfe) {
        Response response = Response.errorResonse(NOT_FOUND, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }}
