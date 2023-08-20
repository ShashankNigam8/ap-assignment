package com.apassignemnt.ap.service;

import com.apassignemnt.ap.entity.Country;
import com.apassignemnt.ap.entity.CustomPageable;

import java.util.List;

public interface CountryService {

    List<Country> findCountryByName(String name);

    List<String>  getCountries(Integer population, Integer area, String language, CustomPageable pageable);
}
