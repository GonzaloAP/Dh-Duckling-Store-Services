package com.aguilar.dhtest.ducklingStore.warehouse.repository;

import com.aguilar.dhtest.ducklingStore.warehouse.entity.DucklingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DucklingRepository extends JpaRepository<DucklingEntity,Long> {

    List<DucklingEntity> findAllByIsDeleted(Boolean isDeleted);

    Optional<DucklingEntity> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    DucklingEntity findByPriceAndColorAndSizeAndIsDeleted(Double price, String color, String size, Boolean isDeleted);
}
