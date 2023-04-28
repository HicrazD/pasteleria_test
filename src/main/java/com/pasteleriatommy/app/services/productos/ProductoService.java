package com.pasteleriatommy.app.services.productos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pasteleriatommy.app.entities.Producto;
import com.pasteleriatommy.app.exepciones.ResourceNotFoundException;

public interface ProductoService {

	
	//Listar Productos normal
	List<Producto> listarProductos();
	
	//Listar productos con paginacion
	Page<Producto> listarProductosPaginados(int numeroPagina, int tamaPagina);
	
	//Create Producto
	Producto guardarProducto(Producto producto);
	
	//Read Producto
	Optional<Producto> buscarproductoPorId(Long productoId);
	
	//Update producto
	void actualizarProducto(Long productoId, Producto producto) throws ResourceNotFoundException;
	
	//Delete Producto
	void eliminarProductoPorId(Long productoId) throws ResourceNotFoundException;
	
	
}
