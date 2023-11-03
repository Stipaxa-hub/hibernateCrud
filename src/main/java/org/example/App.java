package org.example;

import org.example.dao.impl.CountryDaoImpl;
import org.example.model.Country;
import org.example.service.CountryService;
import org.example.service.impl.CountryServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Country country = new Country();
        country.setName("USA");
        CountryService countryService = new CountryServiceImpl(new CountryDaoImpl());
        countryService.add(country);
        System.out.println(countryService.getAll());
        Country country1 = new Country();
        country1.setName("UK");
        countryService.updateById(1L, country1 );
        System.out.println(countryService.getAll());

    }
}
