package com.example.BusTransport.controller;

import com.example.BusTransport.domain.Line;
import com.example.BusTransport.exception.LineNotFoundException;
import com.example.BusTransport.service.LineService;
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
@Tag(name = "Lineas", description = "Listado de lineas")
public class LineController {

    private final Logger logger = LoggerFactory.getLogger(LineController.class);

    @Autowired
    private LineService lineservice;

    @Operation(summary = "Obtiene el listado de las lineas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de lineas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Line.class)))),
    })
    @GetMapping(value = "/lines", produces = "application/json")
    public ResponseEntity<ArrayList<Line>> getLines(){
        logger.info("inicio getLines");
        logger.info("fin getLines");
        return new ResponseEntity<>(lineservice.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una linea por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la linea", content = @Content(schema = @Schema(implementation = Line.class))),
            @ApiResponse(responseCode = "404", description = "La linea no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/lines/{id}", produces = "application/json")
    public ResponseEntity<Line> getLine(@PathVariable Integer id) {
        Line line = lineservice.findById(id)
                .orElseThrow(() -> new LineNotFoundException(id));

        return new ResponseEntity<>(line, HttpStatus.OK);
    }

    @Operation(summary = "Almacena una linea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "linea almacenada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/lines", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Line> addline(@RequestBody Line line){
        if (lineservice.findById(line.getId()).isEmpty()){
            new LineNotFoundException(line.getId());
        }
        logger.info(line.toString());
        return new ResponseEntity<>(lineservice.save(line), HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina una linea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la linea", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La linea no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/lines/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteLine(@PathVariable Integer id) {
        lineservice.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "actualiza el nombre de una linea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el nombre de la linea", content = @Content(schema = @Schema(implementation = Line.class))),
            @ApiResponse(responseCode = "404", description = "La linea no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/Lines/{name}/change-code", produces = "application/json")
    public ResponseEntity<Line> modifyLineName(@PathVariable Integer id, @RequestBody String name) {
        Line line = lineservice.findById(id)
                .orElseThrow(() -> new LineNotFoundException(id));
        line.setName(name);
        return new ResponseEntity<>(lineservice.save(line), HttpStatus.CREATED);
    }

    @Operation(summary = "actualiza una linea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo la linea", content = @Content(schema = @Schema(implementation = Line.class))),
            @ApiResponse(responseCode = "404", description = "La linea de autobus no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/modifyLine", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Line> modifyLine(@RequestBody Line line){
        Integer id = line.getId();
        line = lineservice.findById(id)
                .orElseThrow(() -> new LineNotFoundException(id));
        logger.info(line.toString());
        return new ResponseEntity<>(lineservice.save(line), HttpStatus.CREATED);
    }

    @ExceptionHandler(LineNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(LineNotFoundException lnfe) {
        Response response = Response.errorResonse(NOT_FOUND, lnfe.getMessage());
        logger.error(lnfe.getMessage(), lnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
