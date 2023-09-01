package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.model.Libro;
import com.company.intecap.apibooks.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;
public interface ILibroService {

    //mostrar todos los libros
    public ResponseEntity<LibroResponseRest> buscarLibros();


}
