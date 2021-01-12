package com.demo.demo.repository;

import com.demo.demo.entity.PaymentDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailRepository extends CrudRepository<PaymentDetailEntity, Long> {
}
