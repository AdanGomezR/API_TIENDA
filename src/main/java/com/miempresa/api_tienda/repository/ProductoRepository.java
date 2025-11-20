package com.miempresa.api_tienda.repository;

import com.miempresa.api_tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    List<Producto> findByCategoria(String categoria);
    
    List<Producto> findByStockLessThan(Integer stock);
}
