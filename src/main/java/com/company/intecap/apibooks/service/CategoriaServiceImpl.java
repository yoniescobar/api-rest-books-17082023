package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.dao.ICategoriaDao;
import com.company.intecap.apibooks.model.Categoria;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //@Service es una anotacion que se utiliza para marcar una clase como un bean de servicio
public class CategoriaServiceImpl implements ICategoriaService {
    //errores capturar en la consola
    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    //el log es para mostrar en consola los errores

    @Autowired //inyeccion de dependencias
    private ICategoriaDao categoriaDao;


    @Override
    @Transactional(readOnly = true)
    //para que sea de solo lectura (package   import org.springframework.transaction.annotation.Transactional;)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        //aqui se implementa la logica de negocio

        log.info("inicio metodo buscarCategorias()");
        CategoriaResponseRest response = new CategoriaResponseRest(); //instancia de la clase CategoriaResponseRest

        try {
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll(); //busca todas las categorias findAll(): devuelve un iterable de categorias (List<Categoria>)
            response.getCategoriaResponse().setCategorias(categoria);
            response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "500", "Error al consultar categorias");
            log.error("Error al consultar categorias {}", e.getMessage());
            e.getStackTrace(); //para mostrar el error en consola
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
        log.info("inicio metodo buscarPorId()");
        CategoriaResponseRest response = new CategoriaResponseRest(); //instancia de la clase CategoriaResponseRest
        List<Categoria> list = new ArrayList<>(); //lista de categorias

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id); //busca una categoria por id

            if (categoria.isPresent()) { //si la categoria existe
                list.add(categoria.get()); //agrega la categoria a la lista
                response.getCategoriaResponse().setCategorias(list); //setea la lista de categorias a la clase CategoriaResponseRest
            } else {
                log.error("Error al consultar categoria {}", id);
                response.setMetadata("Respuesta no ok", "404", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND); //error 404
            }
        } catch (Exception e) {
            log.error("Error al consultar categoria {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al consultar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }

        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //respuesta exitosa
    }

    @Override
    @Transactional
    //Rollback: si una de las operaciones falla, se deshacen todas las operaciones -- commit: si todo esta bien, se guardan todas las operaciones
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
        log.info("inicio metodo crear() Categoria");

        CategoriaResponseRest response = new CategoriaResponseRest(); //instancia de la clase CategoriaResponseRest
        List<Categoria> list = new ArrayList<>(); //lista de categorias

        try {

            Categoria categoriaGuardada = categoriaDao.save(categoria); //guarda la categoria en la base de datos


            if (categoriaGuardada != null) { //si la categoria se guardo correctamente
                list.add(categoriaGuardada); //agrega la categoria a la lista
                response.getCategoriaResponse().setCategorias(list); //setea la lista de categorias a la clase CategoriaResponseRest
            } else {
                log.error("Error al crear categoria {}", categoria.toString());
                response.setMetadata("Respuesta no ok", "400", "Categoria no creada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400
            }
        } catch (Exception e) {
            log.error("Error al crear categoria {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al crear categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }
        response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //respuesta exitosa
    }

    @Override
    @Transactional
    //Rollback: si una de las operaciones falla, se deshacen todas las operaciones -- commit: si todo esta bien, se guardan todas las operaciones
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
        log.info("inicio metodo actualizar() Categoria");

        CategoriaResponseRest response = new CategoriaResponseRest(); //instancia de la clase CategoriaResponseRest
        List<Categoria> list = new ArrayList<>(); //lista de categorias

        try {
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id); //busca una categoria por id
            //Optional: porque puede que no exista la categoria

            if (categoriaBuscada.isPresent()) { // si encontro la categoria
                //Setear todos los campos que viene en el objeto categoria que llega por parametro(id, nombre, descripcion)
                categoriaBuscada.get().setNombre(categoria.getNombre()); //setea el nombre de la categoria
                categoriaBuscada.get().setDescripcion(categoria.getDescripcion()); //setea la descripcion de la categoria

                Categoria categoriaActualizada = categoriaDao.save(categoriaBuscada.get()); //primero se busca la categoria por id y luego se guarda en la base de datos
                //Categoria: es la categoria que se va a guardar en la base de datos

                if (categoriaActualizada != null) { //si categoriaActualizada es diferente de null es porque se actualizo correctamente la categoria
                    list.add(categoriaActualizada); //agrega la categoria a la lista
                    response.getCategoriaResponse().setCategorias(list); //setea la lista de categorias a la clase CategoriaResponseRest
                } else {
                    log.error("Error al actualizar categoria {}", categoria.toString());
                    response.setMetadata("Respuesta no ok", "400", "Categoria no actualizada");
                    return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400

                }
            } else {
                log.error("Error al actualizar categoria {}", categoria.toString());
                response.setMetadata("Respuesta no ok", "400", "Categoria no actualizada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST); //error 400
            }
        } catch (Exception e) {
            log.error("Error al actualizar categoria {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al actualizar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500
        }
        response.setMetadata("Respuesta ok", "200", "Categoria Actualizada");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //respuesta exitosa
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        log.info("inicio metodo actualizar() Categoria");
        CategoriaResponseRest response = new CategoriaResponseRest(); //instancia de la clase CategoriaResponseRest

        try {
            Optional<Categoria> categoria= categoriaDao.findById(id); //busca una categoria por id

            if(categoria.isPresent()){ //si la categoria existe
                categoriaDao.delete(categoria.get()); //elimina la categoria por id
            }else{
                log.error("Error al eliminar categoria {}", id);
                response.setMetadata("Respuesta no ok", "400", "Categoria no eliminada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND); //error 404
            }

        }catch (Exception e){
            log.error("Error al Eliminar categoria {}", e.getMessage());
            response.setMetadata("Respuesta no ok", "500", "Error al Eliminar categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //error 500

        }
        response.setMetadata("Respuesta ok", "200", "Categoria Eliminada");
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //respuesta exitosa

    }


}
