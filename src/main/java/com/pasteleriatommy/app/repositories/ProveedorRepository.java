package com.pasteleriatommy.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pasteleriatommy.app.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{

	Page<Proveedor> findAll(Pageable pageable);
}
