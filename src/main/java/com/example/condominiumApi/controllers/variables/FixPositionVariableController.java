package com.example.condominiumApi.controllers.variables;

import com.example.condominiumApi.dtos.request.FixPositionVariableRequestDto;
import com.example.condominiumApi.dtos.response.FixPositionVariableResponseDto;
import com.example.condominiumApi.services.variables.FixPositionVariableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/fix-position-variable")
@Tag(name = "CRUD - FixPosition - Variable")
public class FixPositionVariableController {

    @Autowired
    private FixPositionVariableService fixPositionVariableService;

    @GetMapping
    public ResponseEntity<List<FixPositionVariableResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(fixPositionVariableService.findAll());
    }

    @GetMapping("/variables-solid-works")
    public List<String> getAllSwTypesVariables() {
        // Recupera todas as variáveis cadastradas em FixPositionVariable
        return fixPositionVariableService.getAllSwTypesVariables();
    }

    @GetMapping("/variables-tree")
    public List<String> getAllTreeTypesVariables() {
        // Recupera todas as variáveis cadastradas em FixPositionVariable
        return fixPositionVariableService.getAllTreeTypesVariables();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixPositionVariableResponseDto> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fixPositionVariableService.findById(id));
    }

    @PostMapping
    public ResponseEntity<List<FixPositionVariableResponseDto>> save(@RequestBody List<FixPositionVariableRequestDto> dataList) {
        List<FixPositionVariableResponseDto> savedFixPositionVariableResponseDtos = fixPositionVariableService.save(dataList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFixPositionVariableResponseDtos);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FixPositionVariableResponseDto> update(@RequestBody FixPositionVariableRequestDto fixPositionVariableInfoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(fixPositionVariableService.update(fixPositionVariableInfoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        fixPositionVariableService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
