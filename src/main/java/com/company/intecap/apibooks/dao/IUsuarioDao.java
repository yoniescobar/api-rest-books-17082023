package com.company.intecap.apibooks.dao;

import com.company.intecap.apibooks.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    //metodo para bucar todos los usuarios por nombre
     public  Usuario findByNombreUsuario(String nombreUsuario);

     //Sentcias JPQL:
    @Query("select u from Usuario u where u.nombreUsuario=?1")
    public Usuario findByNombreUsuario2(String nombreUsuario);





}

