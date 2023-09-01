package com.company.intecap.apibooks.response;

import com.company.intecap.apibooks.model.Libro;
import java.util.List;
public class LibroResponse {

    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
