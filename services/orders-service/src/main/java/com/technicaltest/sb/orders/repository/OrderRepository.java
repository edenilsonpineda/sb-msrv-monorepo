package com.technicaltest.sb.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technicaltest.sb.orders.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
