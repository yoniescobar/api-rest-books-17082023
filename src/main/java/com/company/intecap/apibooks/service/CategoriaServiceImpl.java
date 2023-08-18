package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.dao.ICategoriaDao;
import com.company.intecap.apibooks.model.Categoria;
import com.company.intecap.apibooks.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
    //errores capturar en la consola
    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    //el log es para mostrar en consola los errores

    @Autowired //inyeccion de dependencias
    private ICategoriaDao categoriaDao;


    @Override
    public CategoriaResponseRest buscarCategorias() {
        //aqui se implementa la logica de negocio

        log.info("inicio metodo buscarCategorias()");
        CategoriaResponseRest response = new CategoriaResponseRest(); //instancia de la clase CategoriaResponseRest

        try {
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(categoria);
            response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
            }catch (Exception e){
            response.setMetadata("Respuesta no ok", "500", "Error al consultar categorias");
            log.error("Error al consultar categorias {}", e.getMessage());
            e.getStackTrace(); //para mostrar el error en consola
        }

        return response;
    }
}
