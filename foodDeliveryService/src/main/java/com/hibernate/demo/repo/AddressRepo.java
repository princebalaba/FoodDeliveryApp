package com.hibernate.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.demo.dto.Address;


public interface AddressRepo extends JpaRepository<Address, Long>{

}
