package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.model.Libro;
import com.company.intecap.apibooks.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;
public interface ILibroService {

    //mostrar todos los libros
    public ResponseEntity<LibroResponseRest> buscarLibros();

    //mostrar un libro por id
    public ResponseEntity<LibroResponseRest> buscarLibroPorId(Long id);

    //guardar un libro
    public ResponseEntity<LibroResponseRest> guardarLibro(Libro libro);

    //actualizar un libro
    public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id);

//eliminar un libro
    public ResponseEntity<LibroResponseRest> eliminarLibro(Long id);

}
