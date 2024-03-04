package com.example.condominiumApi.services.variables;

import com.example.condominiumApi.dtos.request.FixPositionVariableRequestDto;
import com.example.condominiumApi.dtos.response.FixPositionVariableResponseDto;
import com.example.condominiumApi.entities.FixPositionVariable;
import com.example.condominiumApi.repositories.FixPositionVariableRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FixPositionVariableService {


    @Autowired
    private FixPositionVariableRepository fixPositionVariableRepository;

    @Transactional
    public List<FixPositionVariableResponseDto> findAll() {
        // Adiciona a ordenação por id
        List<FixPositionVariable> resultList = fixPositionVariableRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return resultList.stream().map(FixPositionVariableResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public FixPositionVariableResponseDto findById(Long id){
        var entity = fixPositionVariableRepository.findById(id);
        return entity.map(FixPositionVariableResponseDto::new).orElse(null);
    }

    @Transactional
    public List<String> getAllSwTypesVariables() {
        // Recupera todas as variáveis cadastradas em FixPositionVariable
        List<FixPositionVariable> allVariables = fixPositionVariableRepository.findAll();

        // Extrai apenas as variáveis de cada entidade FixPositionVariable
        return allVariables.stream()
                .filter(variable -> "SolidWorks".equals(variable.getTypeVariable()))
                .map(FixPositionVariable::getName)
                .distinct() // remover duplicatas
                .collect(Collectors.toList());
    }

    @Transactional
    public List<String> getAllTreeTypesVariables() {
        // Recupera todas as variáveis cadastradas em FixPositionVariable
        List<FixPositionVariable> allVariables = fixPositionVariableRepository.findAll();

        // Extrai apenas as variáveis de cada entidade FixPositionVariable
        return allVariables.stream()
                .filter(variable -> "Tree".equals(variable.getTypeVariable()))
                .map(FixPositionVariable::getName)
                .distinct() // remover duplicatas
                .collect(Collectors.toList());
    }

    public List<FixPositionVariableResponseDto> save(List<FixPositionVariableRequestDto> dataList) {
        List<FixPositionVariable> savedFixPositionVariable = dataList.stream()
                .map(FixPositionVariable::new)
                .map(fixPositionVariableRepository::save)
                .toList();

        return savedFixPositionVariable.stream()
                .map(FixPositionVariableResponseDto::new)
                .collect(Collectors.toList());
    }

    public FixPositionVariableResponseDto update(FixPositionVariableRequestDto dto){
        var entity = fixPositionVariableRepository.findById(dto.id()).orElse(null);
        FixPositionVariable saved;

        if (entity != null){
            entity.setName(dto.name());
            entity.setTypeVariable(dto.typeVariable());
            entity.setValue(dto.value());
            entity.setExecutionOrder(dto.executionOrder());
            saved= fixPositionVariableRepository.save(entity);
        }else{
            saved = null;
        }
        return Stream.of(saved).filter(Objects::nonNull).map(FixPositionVariableResponseDto::new).toList().get(0);
    }

    public void deleteById(Long id) {
        fixPositionVariableRepository.deleteById(id);
    }

}
