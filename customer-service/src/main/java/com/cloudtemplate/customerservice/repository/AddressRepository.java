package com.cloudtemplate.customerservice.repository;

import com.cloudtemplate.customerservice.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
