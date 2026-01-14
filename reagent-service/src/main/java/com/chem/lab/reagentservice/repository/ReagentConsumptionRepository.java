package com.chem.lab.reagentservice.repository;

import com.chem.lab.reagentservice.entity.ReagentConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReagentConsumptionRepository extends JpaRepository<ReagentConsumption, UUID> {

    List<ReagentConsumption> findByReagentId(UUID reagentId);
    List<ReagentConsumption> findByExperimentId(UUID experimentId);

}