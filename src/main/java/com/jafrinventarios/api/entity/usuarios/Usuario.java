package com.jafrinventarios.api.entity.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "alias_usuario", nullable = false, unique = true, length = 20)
    private String aliasUsuario;

    @Column(name = "telefono_usuario", nullable = false, unique = true, length = 10)
    private String telefonoUsuario;

    @Column(name = "correo_usuario", nullable = false, unique = true, length = 100)
    private String correoUsuario;

    @Column(name = "primer_nombre_usuario", nullable = false, length = 30)
    private String primerNombreUsuario;

    @Column(name = "segundo_nombre_usuario", length = 30)
    private String segundoNombreUsuario;

    @Column(name = "primer_apellido_usuario", nullable = false, length = 30)
    private String primerApellidoUsuario;

    @Column(name = "segundo_apellido_usuario", length = 30)
    private String segundoApellidoUsuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "contrasena_usuario", nullable = false, length = 100)
    private String contrasenaUsuario;

    // Aquí mapeamos la relación con la tabla Roles
    @ManyToOne
    @JoinColumn(name = "id_rol_usuario", nullable = false)
    private Rol rol;

    @Column(name = "habilitado", nullable = false)
    private boolean habilitado = true;

    // TODO: Cuando se cree la entidad empresa descomentar estas lineas para hacer la relacion
    /*
    * @ManyToOne
    * @JoinColumn(name = "id_empresa", nullable = false)
    * private Empresa empresa;
    * */


    public Usuario() {
    }

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getPrimerNombreUsuario() {
        return primerNombreUsuario;
    }

    public void setPrimerNombreUsuario(String primerNombreUsuario) {
        this.primerNombreUsuario = primerNombreUsuario;
    }

    public String getSegundoNombreUsuario() {
        return segundoNombreUsuario;
    }

    public void setSegundoNombreUsuario(String segundoNombreUsuario) {
        this.segundoNombreUsuario = segundoNombreUsuario;
    }

    public String getPrimerApellidoUsuario() {
        return primerApellidoUsuario;
    }

    public void setPrimerApellidoUsuario(String primerApellidoUsuario) {
        this.primerApellidoUsuario = primerApellidoUsuario;
    }

    public String getSegundoApellidoUsuario() {
        return segundoApellidoUsuario;
    }

    public void setSegundoApellidoUsuario(String segundoApellidoUsuario) {
        this.segundoApellidoUsuario = segundoApellidoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
