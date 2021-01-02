package com.orangetalents.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orangetalents.bank.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
