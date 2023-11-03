package org.example.dao;

import org.example.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    Country add(Country country);
    Optional<Country> get(Long id);
    List<Country> getAll();
    Country updateById(Long id, Country country);
    void remove(Country country);
}
