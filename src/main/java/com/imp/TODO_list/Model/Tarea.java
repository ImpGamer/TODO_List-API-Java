package com.imp.TODO_list.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "TAREAS")
@Data
@NoArgsConstructor

public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(length = 200)
    private String descripcion;
    @Column
    private boolean completado;
    @Column(length = 10,nullable = false)
    private String fechaCreacion;

    @PrePersist
    protected void setValues() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.fechaCreacion = fechaActual.format(formatter);
        this.completado = false;
    }
    public Tarea(String titulo,String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

}
