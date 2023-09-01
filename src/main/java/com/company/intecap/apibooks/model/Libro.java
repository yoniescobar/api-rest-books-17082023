package com.company.intecap.apibooks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "libro")
public class Libro implements Serializable{
    private static final long serialVersionUID = 1L;
    // esta constante es para que se pueda serializar el objeto y se pueda enviar por la red

    //definimos los atributos de la clase
    @Id //@Id es para que se cree una llave primaria en la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Genera un valor unico para la llave primaria de la tabla

    private Long id;
    private String nombre;
    private String descripcion;

    //campo de la tabla categoria
     //relacion de muchos a uno
    // (un libro pertenece a una categoria pero una categoria puede tener muchos libros)
    @ManyToOne(fetch = FetchType.LAZY) //FetchType.LAZY es para que no se cargue la categoria hasta que se llame (carga perezosa)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //ignora los atributos que no se necesitan(hadler: es para que no se cargue la categoria hasta que se llame)
    private Categoria categoria;

    //Getters y Setters de los atributos de la clase( click derecho, insert code, getter and setter)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
