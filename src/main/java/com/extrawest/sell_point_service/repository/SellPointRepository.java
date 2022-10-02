package com.extrawest.sell_point_service.repository;

import com.extrawest.sell_point_service.model.SellPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellPointRepository extends JpaRepository<SellPoint, Long> {
}
