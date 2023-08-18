package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.response.CategoriaResponseRest;

public interface ICategoriaService {

   // declaramos los servicios que vamos a utilizar
    // metodo que devuelve una lista de categorias
    public CategoriaResponseRest buscarCategorias();
}
