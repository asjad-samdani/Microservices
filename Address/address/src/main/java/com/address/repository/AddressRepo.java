package com.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.address.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
