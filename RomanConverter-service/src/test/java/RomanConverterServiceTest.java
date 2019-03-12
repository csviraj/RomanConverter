import com.romanconverter.service.api.RomanConverterService;
import com.romanconverter.service.impl.RomanConverterServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class RomanConverterServiceTest {

    private RomanConverterService romanConverterService;

    @DisplayName("Happy Path test")
    @Test
    public void testRomanConverterServiceHappyPath(){
        String expectedResult = "MMCMXCIX";
        romanConverterService = new RomanConverterServiceImpl();
        String actualResult =  romanConverterService.convertIntoRomanNumeral(2999);
        Assert.assertEquals(expectedResult,actualResult);
    }

    @DisplayName("Just Below Venculum Threshold")
    @Test
    public void testRomanConverterServiceBelowVenculum(){
        String expectedResult = "MMMCMXCIX";
        romanConverterService = new RomanConverterServiceImpl();
        String actualResult =  romanConverterService.convertIntoRomanNumeral(3999);
        Assert.assertEquals(expectedResult,actualResult);
    }

    @DisplayName("Just Above Venculum Threshold")
    @Test
    public void testRomanConverterServiceAboveVenculum(){
        String expectedResult = "(IV)";
        romanConverterService = new RomanConverterServiceImpl();
        String actualResult =  romanConverterService.convertIntoRomanNumeral(4000);
        Assert.assertEquals(expectedResult,actualResult);
    }

    @DisplayName("Max Value Test")
    @Test
    public void testRomanConverterServiceMaxValue(){
        String expectedResult = "(MMMCMXCIX)CMXCIX";
        romanConverterService = new RomanConverterServiceImpl();
        String actualResult =  romanConverterService.convertIntoRomanNumeral(3999999);
        Assert.assertEquals(expectedResult,actualResult);
    }
    @DisplayName("Above Max Value Test")
    @Test
    public void testRomanConverterServiceAboveMaxValue(){
        romanConverterService = new RomanConverterServiceImpl();
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () ->romanConverterService.convertIntoRomanNumeral(4000000),
                        "Exception Not Thrown");

        Assert.assertTrue(thrown.getMessage().contains("Number Out Of Range. Enter No between " + 1 +" and " + 3999999));
    }

    @DisplayName("Min Value Test")
    @Test
    public void testRomanConverterServiceMinValue(){
        String expectedResult = "I";
        romanConverterService = new RomanConverterServiceImpl();
        String actualResult =  romanConverterService.convertIntoRomanNumeral(1);
        Assert.assertEquals(expectedResult,actualResult);
    }

    @DisplayName("Below Min Value Test")
    @Test
    public void testRomanConverterServiceBelowMinValue(){
        romanConverterService = new RomanConverterServiceImpl();
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> romanConverterService.convertIntoRomanNumeral(0),
                        "Exception Not Thrown");

        Assert.assertTrue(thrown.getMessage().equals("Number Out Of Range. Enter No between " + 1 +" and " + 3999999));
    }

    @DisplayName("Negative Value Test")
    @Test
    public void testRomanConverterServiceNegativeValue(){
        romanConverterService = new RomanConverterServiceImpl();
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () ->romanConverterService.convertIntoRomanNumeral(-1),
                        "Exception Not Thrown");

        Assert.assertTrue(thrown.getMessage().contains("Number Out Of Range. Enter No between " + 1 +" and " + 3999999));

    }
}
