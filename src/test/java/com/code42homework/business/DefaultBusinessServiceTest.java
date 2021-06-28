package com.code42homework.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultBusinessServiceTest {

    private final DefaultBusinessService service = new DefaultBusinessService();

    @Test
    void testBuildResult(){
        List<String> testData = List.of("monkey", "2", "cow", "Repeat element 0, 7 times", "dog", "moose", "50");

        Result actualResult = service.buildResult(testData);
        Result expected = testBuildResultExpectedResult();

        assertEquals(expected.sum(), actualResult.sum());
        assertEquals(expected.average(), actualResult.average());
        assertEquals(expected.fractionNumeric(), actualResult.fractionNumeric());
        assertEquals(expected.plainStringCounts(), actualResult.plainStringCounts());
    }

    private Result testBuildResultExpectedResult(){
        Result expectedResult = new Result();
        expectedResult.setSum(52.0);
        expectedResult.setAverage(26.0);
        expectedResult.setFractionNumber(.2);
        expectedResult.setPlainStringCount("moose:1;monkey:8;dog:1;cow:1");
        return expectedResult;
    }

    @Test
    void testBuildResultNoNumbers(){
        List<String> testData = List.of("Cow", "Pony", "Sheep");

        Result actualResult = service.buildResult(testData);
        Result expected = testBuildResultNoNumbersExpectedResult();

        assertEquals(expected.sum(), actualResult.sum());
        assertEquals(expected.average(), actualResult.average());
        assertEquals(expected.fractionNumeric(), actualResult.fractionNumeric());
        assertEquals(expected.plainStringCounts(), actualResult.plainStringCounts());
    }

    private Result testBuildResultNoNumbersExpectedResult(){
        Result expectedResult = new Result();
        expectedResult.setSum(0.0);
        expectedResult.setAverage(0.0);
        expectedResult.setFractionNumber(0.0);
        expectedResult.setPlainStringCount("Sheep:1;Pony:1;Cow:1");
        return expectedResult;
    }

    @Test
    void testBuildResultAllNumbers(){
        List<String> testData = List.of("1", "2", "4", "8", "5");

        Result actualResult = service.buildResult(testData);
        Result expected = testBuildResultAllNumbersExpectedResult();

        assertEquals(expected.sum(), actualResult.sum());
        assertEquals(expected.average(), actualResult.average());
        assertEquals(expected.fractionNumeric(), actualResult.fractionNumeric());
        assertEquals(expected.plainStringCounts(), actualResult.plainStringCounts());
    }

    private Result testBuildResultAllNumbersExpectedResult(){
        Result expectedResult = new Result();
        expectedResult.setSum(20.0);
        expectedResult.setAverage(4.0);
        expectedResult.setFractionNumber(1.0);
        expectedResult.setPlainStringCount("");
        return expectedResult;
    }

    @Test
    void testBuildResultEmptyList(){
        Result actualResult = service.buildResult(List.of());
        assertEquals(new Result(), actualResult);
    }

    @Test
    void testBuildResultNullInput(){
        Result actualResult = service.buildResult(null);
        assertEquals(new Result(), actualResult);
    }

    @Test
    void testIsNumber(){
        assertTrue(service.isNumber("23"));
    }

    @Test
    void testIsNumberFalse(){
        assertFalse(service.isNumber("ImNotANumber"));
    }

    @Test
    void testIsNumberNull(){
        assertFalse(service.isNumber(null));
    }

}
