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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibroPorId(Long id) {
        log.info("Inicio metodo buscarLibroPorId()");
        LibroResponseRest response = new LibroResponseRest();

        List<Libro> list = new ArrayList<>();

        try{
            Optional<Libro> libro = libroDao.findById(id);

            if (libro.isPresent()){
                list.add(libro.get());
                response.getLibroResponse().setLibros(list);
            }else{
                log.error("Error al consultar libro: {}", id);
                response.setMetadata("Respuesta no ok", "404", "Libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.error("Error al consultar libro: {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al consultar libro");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> guardarLibro(Libro libro) {
        log.info("Inicio metodo guardarLibro()");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();

        try{

            Libro libroGuardado = libroDao.save(libro);

            if(libroGuardado != null){
                list.add(libroGuardado);
                response.getLibroResponse().setLibros(list);
            }else{
                log.error("Error al guardar libro: {}", libro.toString());
                response.setMetadata("Respuesta no ok", "400", "Error al guardar libro");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al guardar libro: {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al guardar libro");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional //Rollback: si falla una de las transacciones, se deshacen todas las transacciones  y Commit: si la transaccion es exitosa, se guardan todas las transacciones
    public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id) {
        log.info("Inicio metodo actualizarLibro()");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();

        try{
            Optional<Libro> libroBuscado = libroDao.findById(id); //busca el libro por id

            if(libroBuscado.isPresent()){
                libroBuscado.get().setNombre(libro.getNombre());
                libroBuscado.get().setDescripcion(libro.getDescripcion());
                libroBuscado.get().setCategoria(libro.getCategoria());

                Libro libroActualizado = libroDao.save(libroBuscado.get());

                if(libroActualizado != null){
                    list.add(libroActualizado);
                    response.getLibroResponse().setLibros(list);
                }else{
                    log.error("Error al actualizar libro: {}", libro.toString());
                    response.setMetadata("Respuesta no ok", "400", "Error al actualizar libro");
                    return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }

        }catch (Exception e){
            log.error("Error al actualizar libro: {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al actualizar libro");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> eliminarLibro(Long id) {
        log.info("Inicio metodo eliminarLibro()");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();

        try{
            Optional <Libro> libroBuscado = libroDao.findById(id);

            if(libroBuscado.isPresent()){
                libroDao.delete(libroBuscado.get());
                response.setMetadata("Respuesta ok", "200", "Libro eliminado");
            }else{
                log.error("Error al eliminar libro: {}", id);
                response.setMetadata("Respuesta no ok", "404", "Libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.error("Error al eliminar libro: {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al eliminar libro");
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
    }
}
