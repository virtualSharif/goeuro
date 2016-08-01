package com.goeuro;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.goeuro.business.BusinessService;
import com.goeuro.model.CityDTO;
import com.goeuro.rest.ApiEndPointAccessor;

/**
 * This is the entry point to our Project/Application.
 *
 * @author Sharif Malik
 *
 */

@SpringBootApplication
public class Application implements CommandLineRunner
{

    @Autowired
    private ApiEndPointAccessor apiEndPointAccessor;

    @Autowired
    private BusinessService businessService;

    // reads data from application.yml
    @Value("${output.filename:null}")
    private String outputFileName;

    @Value("${endpoint.url:null}")
    private String URL;

    public static void main(String args[]) throws IOException
    {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws Exception
    {
        String cityName = businessService.getCityNameFromArguments(args);
        List<CityDTO> cityDTOs = apiEndPointAccessor.consumeREST(URL, cityName);
        businessService.writeToCSVfile(cityDTOs, outputFileName);
    }

}