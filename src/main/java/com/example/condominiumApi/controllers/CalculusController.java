package com.example.condominiumApi.controllers;

import com.example.condominiumApi.dtos.request.FixPositionVariableRequestDto;
import com.example.condominiumApi.dtos.result.FixPositionVariableResultDto;
import com.example.condominiumApi.services.CalculateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/calculus")
@Tag(name = "Calculus")
public class CalculusController {

    @Autowired
    private CalculateService calculateService;

    @GetMapping("/conditional")
    public ResponseEntity<Double> conditionalTest(){
        return ResponseEntity.status(HttpStatus.OK).body(calculateService.calculation());
    }

    @GetMapping("/resultCalculus")
    public ResponseEntity<FixPositionVariableResultDto> calculate(){
        FixPositionVariableResultDto result = calculateService.calculateResult();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
