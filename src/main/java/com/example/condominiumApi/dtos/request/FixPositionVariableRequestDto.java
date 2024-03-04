package com.example.condominiumApi.dtos.request;

public record FixPositionVariableRequestDto(Long id, String name, String typeVariable, String value, Long executionOrder) {
}
