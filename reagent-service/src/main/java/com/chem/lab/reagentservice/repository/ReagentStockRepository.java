package com.chem.lab.reagentservice.repository;

import com.chem.lab.reagentservice.entity.ReagentStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReagentStockRepository extends JpaRepository<ReagentStock, UUID> {

    List<ReagentStock> findByReagentId(UUID reagentId);

    @Query("SELECT SUM(r.quantity) FROM ReagentStock r WHERE r.reagentId = :reagentId AND r.active = true")
    Double sumQuantityByReagentId(@Param("reagentId") UUID reagentId);

    List<ReagentStock> findActiveByReagentIdOrderByReceivedAt(UUID reagentId);
}
