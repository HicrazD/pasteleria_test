package com.pasteleriatommy.app.services.proveedores;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pasteleriatommy.app.entities.Proveedor;
import com.pasteleriatommy.app.exepciones.ResourceNotFoundException;
import com.pasteleriatommy.app.repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService{
	
	@Autowired
	private ProveedorRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(ProveedorServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> listarProveedores() {
		log.info("ListarProveedoresImpl");
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Proveedor> listarProveedoresPorPaginacion(int numeroPagina, int tamPagina) {
		
		log.info("listarProveedoresPorPaginacionImpl");
		Pageable pageable = PageRequest.of(numeroPagina, tamPagina);
		return repository.findAll(pageable);
	}

	@Override
	@Transactional
	public Proveedor guardarProveedor(Proveedor proveedor) {
		
		return repository.save(proveedor);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Proveedor> buscarProveedorPorId(Long proveedorId) {
		
		return repository.findById(proveedorId);
	}

	@Override
	@Transactional
	public void actualizarProveedor(Long proveedorId, Proveedor proveedor) throws ResourceNotFoundException {
		Optional<Proveedor> proveedorDb = repository.findById(proveedorId);
		if(proveedorDb.isPresent()) {
			proveedor.setId(proveedorId);
			repository.save(proveedor);
			log.info("Proveedor Actualizado:{}",proveedor);
		}
		else {
			throw new ResourceNotFoundException("Proveedor con ID" + proveedorId + " No encontrado");
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public void eliminarProveedor(Long proveedorId) throws ResourceNotFoundException {
		try {
			repository.deleteById(proveedorId);
		} catch (Exception e) {
			log.info("Error : {}", e.getMessage());
			throw new ResourceNotFoundException("Proveedor con ID" + proveedorId + " No encontrado");
		}
		
	}

}
