package com.github.kalininaleksandrv.digitincreaserapi.controller;

import com.github.kalininaleksandrv.digitincreaserapi.service.NumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NumberController {

    private NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping("/increase")
    public ResponseEntity<String> increase (@RequestBody String param){
        if (!param.isEmpty() && numberService.checkIfStringContainsOnlyDigits(param)){

            try {
                String result = numberService.doIncreaseByOne(param);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (NumberFormatException e) {
                return new ResponseEntity<>("digits must be in range -2147483648 to 2147483647",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else return new ResponseEntity<>("request must contain digits separated by whitespaces",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
