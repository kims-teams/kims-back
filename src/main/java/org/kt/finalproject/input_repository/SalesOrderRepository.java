package org.kt.finalproject.input_repository;

import org.kt.finalproject.input_entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {
}
