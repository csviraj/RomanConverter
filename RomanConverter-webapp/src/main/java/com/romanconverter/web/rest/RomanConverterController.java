package com.romanconverter.web.rest;

import com.romanconverter.service.api.RomanConverterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class RomanConverterController {

    private static final Logger LOGGER = LogManager.getLogger();
    private final RomanConverterService romanConverterService;

    @Inject
    public RomanConverterController(RomanConverterService romanConverterService) {
        this.romanConverterService = romanConverterService;
    }

    /**
     * Mapping Get request to this method that accepts number and returns a Roman Numeral equivalent.
     * @param number is the query parameter and is a required field. Any request that is not a type of
     *               integer will be returned HTTP 400.
     * @return String containing Roman Number Equivalent for the given arabic number.
     */
    @GetMapping(value = "/romannumeral", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String getRomanNumeral(@RequestParam(name="query", required = true) Integer number){
        long startTime = System.nanoTime();
        String romanNumeral = romanConverterService.convertIntoRomanNumeral(number);
        long stopTime = System.nanoTime();
        LOGGER.info("Get Request for Roman Conversion Response Time: {}", (stopTime-startTime)/1e6);
        return "Roman Numeral Equivalent for number:"+number+ " = " + romanNumeral;
    }
}
