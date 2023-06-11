package org.spring.jabaklah.repository;

import org.spring.jabaklah.entity.ValidatePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidatePaymentRepository extends JpaRepository<ValidatePayment,String> {

    ValidatePayment findByUsername(String username);
}
