package com.github.kalininaleksandrv.digitincreaserapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NumberServiceTest {

    @Spy
    NumberService numberService;

    @Test
    void checkIfStringContainsOnlyDigits() {
        assertTrue(numberService.checkIfStringContainsOnlyDigits( " 1 2 3 "));
        assertFalse(numberService.checkIfStringContainsOnlyDigits( " 1 2 9a9 12345   "));
        assertFalse(numberService.checkIfStringContainsOnlyDigits( " 1 2 9!9 12345   "));
    }

    @Test
    void doIncreaseByOne() {
        String result  = numberService.doIncreaseByOne(" 1 2 999 ");
        assertThat(result.split("\\s+")).containsSequence("2", "3", "1000");
    }
}