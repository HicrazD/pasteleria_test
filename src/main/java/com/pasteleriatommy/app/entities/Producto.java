package com.pasteleriatommy.app.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "el campo nombre no puede ser vacio")
	private String nombre;
	
	//@Min(value = 0,message = "El valor minimo del campo cantidadUnidad es 0")
	@Column(name = "cantidad_unidad")
	@NotEmpty(message = "el campo cantidadUnidad no puede ser vacio")
	private String cantidadUnidad;
	
	@Min(value = 0,message = "El valor minimo del campo existencia es 0")
	private int existencia;
	
	@Min(value = 0,message = "El valor minimo del campo precio_unidad es 0")
	@Column(name = "precio_unidad")
	private double precioUnidad;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;
	
	//Un Producto solo puede tener un proveedor
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
	
	
	@PrePersist
	public void prePersist() 
	{
		this.createAt = new Date();
	}

}
