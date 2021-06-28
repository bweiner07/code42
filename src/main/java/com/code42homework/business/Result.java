package com.code42homework.business;

import java.util.Map;
import java.util.Objects;

public class Result {

    private Double sum;

    private Double average;

    private Map<String, Integer> strings;

    private Double fractionNumber;

    private String plainStringCount;

    public Double sum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double average() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Boolean contains(String input) {
        return strings.containsKey(input);
    }

    public void setStrings(Map<String, Integer> strings) {
        this.strings = strings;
    }

    public Double fractionNumeric() {
        return fractionNumber;
    }

    public void setFractionNumber(Double fractionNumber) {
        this.fractionNumber = fractionNumber;
    }

    public String plainStringCounts() {
        return plainStringCount;
    }

    public void setPlainStringCount(String plainStringCount) {
        this.plainStringCount = plainStringCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(sum, result.sum) && Objects.equals(average, result.average) && Objects.equals(strings, result.strings) && Objects.equals(fractionNumber, result.fractionNumber) && Objects.equals(plainStringCount, result.plainStringCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, average, strings, fractionNumber, plainStringCount);
    }
}
