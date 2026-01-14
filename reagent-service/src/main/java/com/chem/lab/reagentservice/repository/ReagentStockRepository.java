package com.chem.lab.reagentservice.repository;

import com.chem.lab.reagentservice.entity.ReagentStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReagentStockRepository extends JpaRepository<ReagentStock, UUID> {

    List<ReagentStock> findByReagentId(UUID reagentId);
    Double sumActiveQuantity(UUID reagentId);
    List<ReagentStock> findActiveByReagentIdOrderByReceivedAt(UUID reagentId);
}