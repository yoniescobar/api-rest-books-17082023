package com.company.intecap.apibooks.controllers;

import com.company.intecap.apibooks.response.LibroResponseRest;
import com.company.intecap.apibooks.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1") //prefijo de la ruta de la api rest  http://localhost:8080/api/v1
public class LibroRestController {

    @Autowired //inyeccion de dependencias
    private ILibroService service;

    @GetMapping("/libros") //ruta de la api rest http://localhost:8080/api/v1/libros
    public ResponseEntity<LibroResponseRest> buscarLibros(){
        return service.buscarLibros();
    }
}
