package com.company.intecap.apibooks.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity // esta anotacion es para que se cree una tabla en la base de datos
@Table(name = "categoria") // esta anotacion es para que se cree una tabla en la base de datos
public class Categoria  implements Serializable {

    private static final long serialVersionUID = 1L;
    // esta constante es para que se pueda serializar el objeto y se pueda enviar por la red

    @Id //@Id es para que se cree una llave primaria en la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Genera un valor unico para la llave primaria de la tabla

    //definimos los atributos de la clase
    private Long id;
    private String nombre;
    //tamaño de la columna
   // @Column(name = "descripcion", length = 200)
    private String descripcion;

    //definimos los metodos get y set de los atributos de la clase (encapsulamiento)

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
}
