package com.code42homework.business;

import java.util.List;

public interface BusinessService {

    /**
     *
     * @param input list of strings that is either a word, number or command
     * @return Result object that has the sum and average of all numbers,
     * the fractional number of items in the list that are numbers,
     * the count of all words in reverse alphabetical order including the result of all commands
     */
    Result buildResult(List<String> input);
}
