package com.example.condominiumApi.entities;

import com.example.condominiumApi.dtos.request.FixPositionVariableRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FixPositionVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String typeVariable;
    //types of variables:
    //*Primitive
    //*Conditionals
    private String value;
    private Long executionOrder;

    public FixPositionVariable(FixPositionVariableRequestDto data){
        this.id = data.id();
        this.name = data.name();
        this.typeVariable = data.typeVariable();
        this.value = data.value();
        this.executionOrder = data.executionOrder();
    }

}