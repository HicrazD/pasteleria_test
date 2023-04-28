package com.pasteleriatommy.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pasteleriatommy.app.entities.Proveedor;
import com.pasteleriatommy.app.services.proveedores.ProveedorService;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

	private static final Logger log = LoggerFactory.getLogger(ProveedorController.class);
	
	@Autowired
	private ProveedorService service;
	
	@GetMapping
	public ResponseEntity<List<Proveedor>> listarProveedores(){
		log.info("Handler listarProveedores");
		
		return ResponseEntity.ok(service.listarProveedores());
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<Page<Proveedor>> listarProveedoresPaginados(
			@RequestParam(defaultValue = "0") int numeroPagina,
			@RequestParam(defaultValue = "10") int tamPagina){
		log.info("Handler ListarProveedoresPaginados");
		Page<Proveedor> proveedoresPaginados = service.listarProveedoresPorPaginacion(numeroPagina, tamPagina);
		return ResponseEntity.ok(proveedoresPaginados);
	}
	
	@PostMapping
	public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor){
		log.info("Handler guardarProveedor");
		Proveedor nuevoProveedor = service.guardarProveedor(proveedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
	}
}
