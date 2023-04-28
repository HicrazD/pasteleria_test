package com.pasteleriatommy.app.services.productos;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pasteleriatommy.app.entities.Producto;
import com.pasteleriatommy.app.exepciones.ResourceNotFoundException;
import com.pasteleriatommy.app.repositories.ProductoRepository;

@Service

public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository repository;

	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Producto> listarProductos() {

		log.info("listarProductosImpl");
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> listarProductosPaginados(int numeroPagina, int tamaPagina) {
		log.info("ListarProductosPaginadosImpl");
		Pageable pageable = PageRequest.of(numeroPagina, tamaPagina, Direction.ASC);
		return repository.findAll(pageable);
	}

	@Override
	@Transactional
	public Producto guardarProducto(Producto producto) {
		log.info("guardarProductoImpl");
		return repository.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> buscarproductoPorId(Long productoId) {

		log.info("buscarProductoPorIdImpl");
		return repository.findById(productoId);
	}

	@Override
	@Transactional
	public void actualizarProducto(Long productoId, Producto producto) throws ResourceNotFoundException {
		log.info("actualizarProductoImpl");
		Optional<Producto> productoDb = repository.findById(productoId);
		try {
			if (productoDb.isPresent()) {
				producto.setId(productoId);
				
				repository.save(producto);
				log.info("Produto Actualizado :{}",producto.getNombre());
			}else {
				log.error("Producto no encontrado con ID:{}",productoId);
				throw new ResourceNotFoundException("Producto no encontrado con ID:" + productoId);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	@Override
	@Transactional
	public void eliminarProductoPorId(Long productoId) throws ResourceNotFoundException{
		log.info("eliminarProductoPorIdImpl");
		try {
			repository.deleteById(productoId);
		} catch (Exception e) {
			log.error("Error: {}",e.getMessage());
			throw new ResourceNotFoundException("Producto no encontrado con ID:" + productoId);
		}

	}

}
