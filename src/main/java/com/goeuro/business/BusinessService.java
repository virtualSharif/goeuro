package com.goeuro.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.goeuro.Application;
import com.goeuro.model.CityDTO;

/**
 * This is the business service which contains actual logic.
 *
 * @author Sharif Malik
 *
 */

@Component
public class BusinessService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @SuppressWarnings("resource")
    public void writeToCSVfile(List<CityDTO> cities, String fileName) throws IOException
    {

        Writer writer = null;

        File outputFile = new File(fileName);

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));

        if (cities.isEmpty())
        {
            writer.write("OOPS! THE ENDPOINT FOUND ZERO RESULTS FOR THE SPECIFIED CITY!");
            LOGGER.info(fileName + " file successfully created; But zero results found for the specified city!");
        }
        else
        {
            writer.write("_id, name, type, latitude, longitude\n");

            StringBuffer stringBuffer = new StringBuffer();
            for (CityDTO city : cities)
            {
                stringBuffer.append(city.get_id().toString()).append(", ").append(city.getName()).append(", ").append(city.getType()).append(", ").append(city.getGeo_position().getLatitude().toString())
                        .append(", ").append(city.getGeo_position().getLongitude().toString()).append("\n");
            }
            writer.write(stringBuffer.toString());
            LOGGER.info(fileName + " file was created successfully!");
        }
        writer.flush();
    }

    public String getCityNameFromArguments(String[] args)
    {
        if (args == null || args.length == 0 || args[0] == null || args[0].isEmpty())
        {
            LOGGER.info("City name MUST be specified!");
            throw new IllegalArgumentException("City name MUST be specified!");
        }

        String inputString = "";

        if (args.length == 1)
        {
            // one string city name like "Mumbai", "Goa", "Berlin"
            inputString = args[0].trim();
        }
        else
        {
            // more than one string city name like "Las Vegas"
            for (String arg : args)
            {
                inputString = inputString + (arg != null ? arg : "") + " ";
            }
        }

        return inputString;
    }

}
