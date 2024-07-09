package com.fsm.TP6_bank_account_service.repositories;

import com.fsm.TP6_bank_account_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
