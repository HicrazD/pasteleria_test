package com.pasteleriatommy.app.services.proveedores;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.pasteleriatommy.app.entities.Proveedor;
import com.pasteleriatommy.app.exepciones.ResourceNotFoundException;

public interface ProveedorService {

	List<Proveedor> listarProveedores();
	
	Page<Proveedor> listarProveedoresPorPaginacion(int numeroPagina, int tamPagina);
	
	Proveedor guardarProveedor(Proveedor proveedor);
	
	Optional<Proveedor> buscarProveedorPorId(Long proveedorId);
	
	void actualizarProveedor(Long proveedorId,Proveedor proveedor) throws ResourceNotFoundException;
	
	void eliminarProveedor(Long proveedorId)throws ResourceNotFoundException;
}
