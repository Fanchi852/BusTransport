package com.example.BusTransport.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Lines")
public class Line {

    @Schema(description = "numero de identificacion unico en la base de datos", example = "25", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Schema(description = "nombre de la linea", example = "Jarazmín - El Palo", required = true)
    @Column(unique = true)
    private String name;

    @Schema(description = "largo del recorrido de la linea en km", example = "68,42", required = true)
    @Column
    private Float size;

    @Schema(description = "estado de la linea", example = "true", required = true)
    @Column
    private Boolean acctive;

    @Schema(description = "", example = "25/01/1999 17:25:00", required = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column
    private Date creation;

    @Schema(description = "color representatibo de la linea en los mapas", example = "rojo", required = true)
    @Column
    private String color;

    @OneToMany(mappedBy = "line")
    private List<Bus> buses;

    @OneToMany(mappedBy = "line")
    private List<BusStopsLinesRelation> busstopslinesrelation;
}
