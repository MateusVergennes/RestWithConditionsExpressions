package com.example.condominiumApi.dtos.response;

import com.example.condominiumApi.entities.FixPositionVariable;

public record FixPositionVariableResponseDto(Long id, String name, String typeVariable, String value, Long executionOrder) {

    public FixPositionVariableResponseDto(FixPositionVariable entity){
        this(entity.getId(), entity.getName(), entity.getTypeVariable(), entity.getValue(), entity.getExecutionOrder());
    }

}
