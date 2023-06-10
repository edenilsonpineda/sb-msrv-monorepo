package com.technicaltest.sb.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicaltest.sb.orders.model.ProductOrder;


public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}
