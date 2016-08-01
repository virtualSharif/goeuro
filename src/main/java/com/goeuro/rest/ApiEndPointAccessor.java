package com.goeuro.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.goeuro.Application;
import com.goeuro.model.CityDTO;

/**
 * This is POJO class which can used for data transfer object. Used Lombok for getters , setters, constructors and etc
 *
 * @author Sharif Malik
 *
 */

@Component
public class ApiEndPointAccessor
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public List<CityDTO> consumeREST(String url, String cityName)
    {
        if (isInternetAvailable())
        {
            RestTemplate restTemplate = new RestTemplate();
            List<CityDTO> cityDTOs = Arrays.asList(restTemplate.getForObject(url.concat(cityName), CityDTO[].class));
            return cityDTOs;
        }
        else
        {
            LOGGER.info("No Internet connection, Please provide a connection!");
            throw new IllegalStateException("No Internet connection found, Please provide a connection!");
        }
    }

    private boolean isInternetAvailable()
    {
        try
        {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            return false;
        }
    }

}
