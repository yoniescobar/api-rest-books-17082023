package com.company.intecap.apibooks.response;

public class CategoriaResponseRest extends ResponseRest {
    //extends ResponseRest para heredar los metodos de la clase padre

    private CategoriaResponse categoriaResponse = new CategoriaResponse();
    //metodo que devuelve una lista de categorias hereada de la clase CategoriaResponse

    public CategoriaResponse getCategoriaResponse() {
        return categoriaResponse;
    }

    public void setCategoriaResponse(CategoriaResponse categoriaResponse) {
        this.categoriaResponse = categoriaResponse;
    }

}
