package com.company.intecap.apibooks.dao;

import com.company.intecap.apibooks.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface ILibroDao extends CrudRepository<Libro, Long> {
}
