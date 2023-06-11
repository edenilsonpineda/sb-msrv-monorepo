package com.technicaltest.sb.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicaltest.sb.orders.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
