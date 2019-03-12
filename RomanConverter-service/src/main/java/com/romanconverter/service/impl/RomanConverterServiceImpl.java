package com.romanconverter.service.impl;

import com.romanconverter.service.api.RomanConverterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TreeMap;

import javax.inject.Named;

@Named
public class RomanConverterServiceImpl implements RomanConverterService {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final int VENCULUM_THRESHOLD = 3999;
    public static final int MAX_VALUE = 3999999;
    public static final int MIN_VALUE = 1;
    public static final String OUT_OF_RANGE_ERR_MSG = "Number Out Of Range. Enter No between "
            + MIN_VALUE +" and " + MAX_VALUE;

    private final TreeMap<Integer, String> intToRomanMap;

    /**
     * Default constructor for RomanConverterServiceImpl. We preload the tree map here.
     * Since spring injects singleton object by default, this map will we created and preloaded
     * only once at the startup.
     */
    public RomanConverterServiceImpl(){
        intToRomanMap =  new TreeMap<>();
        intToRomanMap.put(1000000, "M");
        intToRomanMap.put(900000,"CM");
        intToRomanMap.put(500000,"D");
        intToRomanMap.put(400000,"CD");
        intToRomanMap.put(100000,"C");
        intToRomanMap.put(90000, "XC");
        intToRomanMap.put(50000,"L");
        intToRomanMap.put(40000,"XL");
        intToRomanMap.put(10000,"X");
        intToRomanMap.put(9000,"IX");
        intToRomanMap.put(5000, "V");
        intToRomanMap.put(4000, "IV");
        intToRomanMap.put(1000, "M");
        intToRomanMap.put(900, "CM");
        intToRomanMap.put(500, "D");
        intToRomanMap.put(400, "CD");
        intToRomanMap.put(100, "C");
        intToRomanMap.put(90, "XC");
        intToRomanMap.put(50, "L");
        intToRomanMap.put(40, "XL");
        intToRomanMap.put(10, "X");
        intToRomanMap.put(9, "IX");
        intToRomanMap.put(5, "V");
        intToRomanMap.put(4, "IV");
        intToRomanMap.put(1, "I");
    }

    /**
     * Convert the integer value into Roman Numeral equivalent and returns in String format.
     * The function uses a preloaded TreeMap to get associated numeral value. It makes use of floorkey(int)
     * function of the map where it looks up the key less then or equal to the argument. For example
     * 3999 the floorkey would return 1000.
     *
     * @param number in Arabic format that will be converted to Roman Numeral form
     * @return String value which is Roman Numeral representation of the argument. The roman numerals
     * in () denote the venaculum entension
     *
     * @throws IllegalArgumentException if the specified number in arabic format is either less than
     * or equal to zero or greater than 3999999
     */
    @Override
    public final String convertIntoRomanNumeral(Integer number) throws IllegalArgumentException {
        boolean isAboveVinculumThreshold = false;
        StringBuilder romanNumber = new StringBuilder();

        //Check for validity of input.
        if(number > MAX_VALUE || number < MIN_VALUE){
            LOGGER.error("OUT_OF_RANGE_ERR_MSG");
            throw new IllegalArgumentException(OUT_OF_RANGE_ERR_MSG);
        }
        try{
            //Check if the number is greater than 3999. if true, we set vinculum threshold to 'T' and we use  brackets
            // to denote the part of numeral above 3999 instead of bar over letters.
            if(number > VENCULUM_THRESHOLD){
                isAboveVinculumThreshold = true;
                romanNumber.append("(");
            }
            // Here we Loop till the number remains greater than or equal to  Min value (1), use floorkey to extract the highest key
            // less than or equal to the argument from the tree map , retrieve roman numeral value for it
            // and add it to the string builder obj and then reduce it by that floor value.
            while(number >= MIN_VALUE){
                int key = intToRomanMap.floorKey(number);
                romanNumber.append(intToRomanMap.get(key));
                number = number - key;

                //check if the original number was above threshold and the remainder has been reduced
                //below the threhold close the bracket and set flag to false.
                if(number<=3999 && isAboveVinculumThreshold){
                    romanNumber.append(")");
                    isAboveVinculumThreshold=false;
                }
            }
        }catch (Exception e){
            LOGGER.error("An error occured while performing Roman Conversion. ERROR: {}", e.getMessage());
        }

        return romanNumber.toString();
    }
}
