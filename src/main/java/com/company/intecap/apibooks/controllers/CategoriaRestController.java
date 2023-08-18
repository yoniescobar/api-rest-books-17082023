package com.company.intecap.apibooks.controllers;

import com.company.intecap.apibooks.response.CategoriaResponseRest;
import com.company.intecap.apibooks.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")  //prefijo de la ruta de la api rest  http://localhost:8080/api/v1
public class CategoriaRestController {

    //implementar los metodos de la clase CategoriaServiceImpl
    @Autowired //inyeccion de dependencias
    private ICategoriaService service;

    @GetMapping("/categorias") //ruta de la api rest http://localhost:8080/api/v1/categorias
    public CategoriaResponseRest buscarCategorias(){
        return service.buscarCategorias();
    }

}
