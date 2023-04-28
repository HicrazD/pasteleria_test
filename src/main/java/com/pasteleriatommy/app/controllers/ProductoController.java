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

import com.pasteleriatommy.app.entities.Producto;
import com.pasteleriatommy.app.services.productos.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoService service;

	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos() {
		log.info("Handler listarProductos");
		return ResponseEntity.ok(service.listarProductos());
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<Page<Producto>> listarProductosPaginados(
			@RequestParam(defaultValue = "0") int numeroPagina,
			@RequestParam(defaultValue = "10") int tamPagina){
		log.info("Handler listarProductosPaginados");
		Page<Producto> productosPaginados = service.listarProductosPaginados(numeroPagina, tamPagina);
		return ResponseEntity.ok(productosPaginados);
	}

	@PostMapping
	public ResponseEntity<Producto> guardarProduto(@RequestBody Producto producto) {
		log.info("Handler guardarProducto");
		Producto nuevoProducto = service.guardarProducto(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
	}

}
