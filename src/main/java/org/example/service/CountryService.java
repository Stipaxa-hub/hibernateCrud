package org.example.service;

import org.example.model.Country;

import java.util.List;

public interface CountryService {
    Country add(Country country);
    Country get(Long id);
    List<Country> getAll();
    Country updateById(Long id, Country country);
    void remove(Country country);
}
