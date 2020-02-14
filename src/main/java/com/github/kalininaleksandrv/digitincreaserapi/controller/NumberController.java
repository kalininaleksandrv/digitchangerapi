package com.github.kalininaleksandrv.digitincreaserapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NumberController {

    @PostMapping("/increase")
    public ResponseEntity<String> increase (@RequestBody String param){
        return new ResponseEntity<>("Hello from increase " + param, HttpStatus.OK);
    }

    @PostMapping("/decrease")
    public ResponseEntity<String> decrease (@RequestBody String param){
        return new ResponseEntity<>("Hello from decrease " + param, HttpStatus.OK);
    }
}
