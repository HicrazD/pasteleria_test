package com.pasteleriatommy.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pasteleriatommy.app.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	Page<Producto> findAll(Pageable pageable);

}
