package com.apassignemnt.ap.controller;

import com.apassignemnt.ap.entity.Country;
import com.apassignemnt.ap.entity.CustomPageable;
import com.apassignemnt.ap.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api")
@Tag(name = "countries", description = "API operations related to countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country/{name}")
    @Operation(summary = "Get country details by name", description = "returns a list of all countries.")
    ResponseEntity<List<Country>> getCountryByName(@PathVariable(name = "name") String name){
        return ResponseEntity.ok(countryService.findCountryByName(name));
    }

    @GetMapping("/countries/")
    @Operation(summary = "Get country details by different filters", description = "returns a list of all countries.")
    ResponseEntity<List<String>> getCountries(@RequestParam(required = false) Integer population,
                                               @RequestParam(required = false) Integer area,
                                               @RequestParam(required = false) String language,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "asc") String sortOrder){
        return ResponseEntity.ok(countryService.getCountries(population, area, language, new CustomPageable(page, size, sortOrder)));
    }
}
