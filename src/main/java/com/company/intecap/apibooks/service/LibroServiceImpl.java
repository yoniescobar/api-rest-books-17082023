package com.company.intecap.apibooks.service;


import com.company.intecap.apibooks.dao.ILibroDao;
import com.company.intecap.apibooks.model.Libro;
import com.company.intecap.apibooks.response.LibroResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServiceImpl implements ILibroService {
    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    //el log es para mostrar en consola los errores
    @Autowired //inyeccion de dependencias
    private ILibroDao libroDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        log.info("Inicio metodo buscarLibros()");

        LibroResponseRest response = new LibroResponseRest();

        try{
            List<Libro> libros = (List<Libro>) libroDao.findAll();
            response.getLibroResponse().setLibros(libros);

        }catch(Exception e){
            response.setMetadata("Respuesta no ok", "500", "Error al consultar libros");
            log.error("Error al consultar libros: {}", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 500
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
    }
}
