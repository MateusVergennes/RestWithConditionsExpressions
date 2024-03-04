package com.example.condominiumApi.repositories;

import com.example.condominiumApi.entities.FixPositionVariable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixPositionVariableRepository extends JpaRepository<FixPositionVariable, Long> {
    List<FixPositionVariable> findAll(Sort sort);
}
