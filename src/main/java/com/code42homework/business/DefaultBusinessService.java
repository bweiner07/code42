package com.code42homework.business;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class DefaultBusinessService implements BusinessService {

    //Match example will be 1, 7
    private final Pattern pattern = Pattern.compile("(\\d+),\\s(\\d+)");

    @Override
    public Result buildResult(List<String> input){
        if(isNull(input) || input.isEmpty()){
            return new Result();
        }

        double sum = 0.0;
        int numCount = 0;
        double totalCountOfActualNumbers = 0;
        Map<String, Integer> wordCounts = new HashMap<>();

        for (String item: input) {
            int stringCount = 0;

            if(item.contains(REPEAT_ELEMENT)){
                Matcher matcher = pattern.matcher(item);
                if(matcher.find()){
                    int index = Integer.parseInt(matcher.group(1));
                    int multiplier = Integer.parseInt(matcher.group(2));

                    String valueToBeRepeated = input.get(index);
                    if(isNumber(valueToBeRepeated)){
                        sum += Double.parseDouble(valueToBeRepeated) * multiplier;
                        numCount += multiplier;
                    }
                    else {
                        stringCount = Optional.ofNullable(wordCounts.get(valueToBeRepeated)).orElse(0);
                        wordCounts.put(valueToBeRepeated, (stringCount + multiplier));
                    }
                }
            }
            else if(isNumber(item)) {
                sum+= Double.parseDouble(item);
                numCount++;
                totalCountOfActualNumbers++;
            }
            else {
                stringCount = Optional.ofNullable(wordCounts.get(item)).map(num -> num++).orElse(1);
                wordCounts.put(item, stringCount);
            }
        }

        return getResult(input, sum, numCount, totalCountOfActualNumbers, wordCounts);
    }

    Boolean isNumber(String input){
        if (isNull(input)){
            return false;
        }
        try {
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private Result getResult(List<String> input, double sum, int numCount, double totalCountOfActualNumbers, Map<String, Integer> stringCounts) {
        Result finalResult = new Result();
        finalResult.setSum(sum);
        finalResult.setAverage(setSignificantFiguresOnFloatType(sum / numCount));
        finalResult.setStrings(stringCounts);
        finalResult.setFractionNumber(setSignificantFiguresOnFloatType(totalCountOfActualNumbers / input.size()));
        finalResult.setPlainStringCount(plainStringCount(stringCounts));
        return finalResult;
    }

    private Double setSignificantFiguresOnFloatType(Double number){
        if(number.isNaN())
            return 0.0;

        String fractionNumberOneSigFig = String.valueOf(number);
        return Double.parseDouble(fractionNumberOneSigFig.substring(0, 3));
    }

    private String plainStringCount(Map<String, Integer> stringCounts){
        List<String> sortedKeys = new ArrayList<>(stringCounts.keySet());
        sortedKeys.sort(Collections.reverseOrder());

        StringBuilder stringBuilder = new StringBuilder();
        for (String key: sortedKeys) {
            if(stringBuilder.length() != 0){
                stringBuilder.append(";");
            }
            stringBuilder.append(key).append(":").append(stringCounts.get(key));
        }
        return stringBuilder.toString();
    }

    private static final String REPEAT_ELEMENT = "Repeat element";
}
