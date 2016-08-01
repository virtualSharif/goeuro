package com.goeuro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * This is a Data transfer object of GeoPosition which is used in CityDTO
 *
 * @author Sharif Malik
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GeoPositionDTO
{

    private Double latitude;
    private Double longitude;

}
