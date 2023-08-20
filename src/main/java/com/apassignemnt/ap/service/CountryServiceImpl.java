package com.apassignemnt.ap.service;

import com.apassignemnt.ap.entity.Country;
import com.apassignemnt.ap.entity.CustomPageable;
import com.apassignemnt.ap.exception.InCorrectResponseException;
import com.apassignemnt.ap.exception.NoCountryFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService{
    private final String BASE_URL = "https://restcountries.com/v3.1";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Country> findCountryByName(String name) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL + "/name/")
                .path(name);
        ResponseEntity<Country[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), Country[].class);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        }else{
            throw new InCorrectResponseException("Client error!!!");
        }
    }

    @Override
    public List<String> getCountries(Integer population, Integer area, String language, CustomPageable pageable) {
        //Fetch all countries
        List<Country> allCountries = getAllCountries();

        Stream<Country> filteredCountriesStream = allCountries.stream()
                .filter(country -> (population == null || country.getPopulation() >= population))
                .filter(country -> (area == null || country.getArea() >= area))
                .filter(country -> (language == null || hasLanguage(country.getLanguages(), language)));


        Comparator<Country> comparator = createComparator(pageable.getSortOrder());

        List<Country> filteredAndSortedCountries = filteredCountriesStream
                .sorted(comparator)
                .collect(Collectors.toList());

        List<Country> paginatedCountries = paginateList(filteredAndSortedCountries, pageable);

        if(!CollectionUtils.isEmpty(paginatedCountries)){
            //prepare list of all country name
            List<String> countryNames = paginatedCountries.stream().map(country -> country.getName().getOfficial()).collect(Collectors.toList());

            return countryNames;
        }else{
            throw new NoCountryFoundException("No country Found. Please search with different criteria.");
        }
    }

    private Comparator<Country> createComparator(String sortOrder) {
        Comparator<Country> comparator =  Comparator.comparing(country -> country.getName().getOfficial()); // Default sorting by name
        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        return comparator;

    }

        private boolean hasLanguage(Map<String, String> languages, String targetLanguage) {
        if (languages != null && targetLanguage != null) {
            return languages.values().stream().anyMatch(lang -> lang.equalsIgnoreCase(targetLanguage));
       }
        return false;
    }

    private int compareStrings(String s1, String s2, String sortOrder) {
        int result;
        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            result = s2.compareToIgnoreCase(s1); // Compare in descending order
        } else {
            result = s1.compareToIgnoreCase(s2); // Compare in ascending order
        }
        return result;
    }

    private List<Country> paginateList(List<Country> filteredCountries, CustomPageable pageable) {
        int startIdx = pageable.getPage() * pageable.getSize();
        int endIdx = Math.min(startIdx + pageable.getSize(), filteredCountries.size());

        if (startIdx < endIdx) {
            return filteredCountries.subList(startIdx, endIdx);
        }else{
            return Collections.emptyList();
        }
    }

    private List<Country> getAllCountries() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL + "/all");
        ResponseEntity<Country[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), Country[].class);

        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        }else{
            throw new InCorrectResponseException("Client error!!!");
        }
    }
}
