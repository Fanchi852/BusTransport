package com.example.BusTransport.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Bus_stops")
public class BusStop {

    @Schema(description = "numero de identificacion unico en la base de datos", example = "25", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Nombre de la parada", example = "Atocha", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "cantidad de asientos de la parada", example = "5", required = true)
    @Column
    private Integer seat;

    @Schema(description = "area des area de la paradaen metros", example = "6,5", required = true)
    @Column
    private Float size;

    @Schema(description = "fecha de la construccion de la parada", example = "25/01/1999 17:25:00", required = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column
    private LocalDate creation;

    @Schema(description = "especifica si la parada tiene techado o no", example = "true", required = true)
    @Column
    private Boolean covering;

    @OneToMany(mappedBy = "busstop")
    private List<BusStopsLinesRelation> busstopslinesrelation;
}
