package com.jafrinventarios.api.entity.usuarios;

import jakarta.persistence.*;

// @Entity le dice a Spring que esta clase es un modelo mapeado a una base de datos
@Entity
// @Table le especifica exactamente el nombre de la tabla en MySQL
@Table(name = "Roles")
public class Rol {

    // @Id indica que este campo es la llave primaria (PRIMARY KEY)
    @Id
    // @GeneratedValue con IDENTITY le dice que es AUTO_INCREMENT
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    // @Column enlaza la variable con la columna, y le damos las mismas restricciones del SQL
    @Column(name = "nombre_rol", nullable = false, length = 30)
    private String nombreRol;

    /*
    IMPORTANTE: Spring JPA exige obligatoriamente un constructor vacío
    para poder construir los objetos por debajo cuando hace consultas.
    */
    public Rol() {
    }

    // Constructor con parámetros (sin el ID, ya que es autogenerado)
    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    // Getters y Setters
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
