package com.goeuro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * This is POJO class which can used for data transfer object. Used Lombok for getters , setters, constructors and etc
 *
 * @author Sharif Malik
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CityDTO
{
    private Long _id;
    private String key;
    private String name;
    private String fullName;
    private String iata_airport_code;
    private String type;
    private String country;
    private Long locationId;
    private boolean inEurope;
    private String countryCode;
    private boolean coreCountry;
    private String distance;
    private GeoPositionDTO geo_position;
}
