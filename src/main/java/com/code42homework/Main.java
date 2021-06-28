package com.code42homework;

import com.code42homework.business.BusinessService;
import com.code42homework.business.DefaultBusinessService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var input = List.of(
                "4",
                "squid",
                "Repeat element 1, 7 times",
                "-4",
                "3",
                "Repeat element 4, 3 times",
                "elephant"
        );

        BusinessService service = new DefaultBusinessService();
        var result = service.buildResult(input);

         System.out.println(result.sum());
         System.out.println(result.average());
         System.out.println(result.contains("squid"));
         System.out.println(result.fractionNumeric());
         System.out.println(result.plainStringCounts());
    }
}