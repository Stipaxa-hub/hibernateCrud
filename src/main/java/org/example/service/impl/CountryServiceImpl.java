package org.example.service.impl;

import org.example.dao.CountryDao;
import org.example.model.Country;
import org.example.service.CountryService;

import java.util.List;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(() -> new RuntimeException("Can't get a country with id: " + id));
    }

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }

    @Override
    public Country updateById(Long id, Country country) {
        return countryDao.updateById(id, country);
    }

    @Override
    public void remove(Country country) {
        countryDao.remove(country);
    }
}
