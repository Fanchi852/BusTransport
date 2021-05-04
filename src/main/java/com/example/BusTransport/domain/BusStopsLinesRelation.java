package com.example.BusTransport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="busstops_lines_relation")
public class BusStopsLinesRelation {

    @Schema(description = "numero de identificacion unico en la base de datos", example = "25", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "busstop_id")
    @JsonBackReference
    private BusStop busstop;

    @ManyToOne
    @JoinColumn(name = "line_id")
    @JsonBackReference
    private Line line;
}
