package com.github.kalininaleksandrv.digitincreaserapi.service;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NumberService {

    public boolean checkIfStringContainsOnlyDigits (String checked) {

        boolean hasNoDigitChars = true;

        for (char c : checked.toCharArray()) {
            if (!Character.isDigit(c) && !Character.isWhitespace(c)) {
                hasNoDigitChars = false;
                break;
            }
        }
        return hasNoDigitChars;
    }

    public String doIncreaseByOne(String param) throws NumberFormatException {

        return Stream.of(param.trim().split("\\s+"))
                .map (Integer::parseInt)
                .map(i -> i+1)
                .map(s -> Integer.toString(s))
                .collect(Collectors.joining(" "));
    }
}
