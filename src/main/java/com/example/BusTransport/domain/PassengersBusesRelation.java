package com.example.BusTransport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="passengers_buses_relation")
public class PassengersBusesRelation {

    @Schema(description = "numero de identificacion unico en la base de datos", example = "25", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @JsonBackReference
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;

    @Schema(description = "momento en el que el pasajero sube al autobus", example = "25/01/1999 17:25:00", required = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "get_on")
    private LocalDate geton;

    @Schema(description = "momento en el que el pasajero baja del autobus", example = "25/01/1999 17:25:00", required = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "get_out")
    private LocalDate gateout;}
