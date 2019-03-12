package com.romanconverter.web.rest;

import com.romanconverter.service.api.RomanConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/romannumeral", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String getRomanNumeral(@RequestParam(name="query", required = true) Integer number){
        long startTime = System.nanoTime();
        String romanNumeral = romanConverterService.convertIntoRomanNumeral(number);
        long stopTime = System.nanoTime();
        LOGGER.info("Get Request for Roman Conversion Response Time: {}", (stopTime-startTime)/1e6);
        return "Roman Numeral Equivalent for number:"+number+ " = " + romanNumeral;
    }
}
