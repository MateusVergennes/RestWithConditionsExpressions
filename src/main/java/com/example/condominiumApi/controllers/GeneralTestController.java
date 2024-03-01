package com.example.condominiumApi.controllers;

import com.example.condominiumApi.services.GeneralTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/GeneralTest")
public class GeneralTestController {

    @Autowired
    private GeneralTest generalTest;

    @GetMapping
    public ResponseEntity<String> generalTest(){
        return ResponseEntity.status(HttpStatus.OK).body(generalTest.opa());
    }

    @GetMapping("/conditional")
    public ResponseEntity<Double> conditionalTest(){
        return ResponseEntity.status(HttpStatus.OK).body(generalTest.calculation());
    }

}
