package com.chem.lab.reagentservice.repository;


import com.chem.lab.reagentservice.entity.Reagent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReagentRepository extends JpaRepository<Reagent, UUID> {
    Optional<Reagent> findByCasNumber(String casNumber);
}