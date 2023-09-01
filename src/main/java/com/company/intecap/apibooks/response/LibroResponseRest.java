package com.company.intecap.apibooks.response;

public class LibroResponseRest extends ResponseRest {

    //extends ResponseRest para heredar los metodos de la clase padre
        private LibroResponse libroResponse = new LibroResponse(); //metodo que devuelve una lista de libros hereada de la clase LibroResponse

        //metodo que devuelve una lista de libros hereada de la clase LibroResponse
        public LibroResponse getLibroResponse() {
            return libroResponse;
        }

        public void setLibroResponse(LibroResponse libroResponse) {
            this.libroResponse = libroResponse;
        }
}

